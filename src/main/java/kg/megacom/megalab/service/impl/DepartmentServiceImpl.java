package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Department;
import kg.megacom.megalab.model.mapper.DepartmentMapper;
import kg.megacom.megalab.model.mapper.OrganizationMapper;
import kg.megacom.megalab.model.mapper.UserMapper;
import kg.megacom.megalab.model.request.CreateDepartmentRequest;
import kg.megacom.megalab.model.request.SetHeadRequest;
import kg.megacom.megalab.model.request.UpdateDepartmentRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.DepartmentRepository;
import kg.megacom.megalab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final OrganizationService organizationService;
    private final UserService userService;
    private final DepartmentUserService departmentUserService;
    private final PositionService positionService;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository,
                                 OrganizationService organizationService,
                                 UserService userService,
                                 DepartmentUserService departmentUserService,
                                 @Lazy PositionService positionService) {
        this.departmentRepository = departmentRepository;
        this.organizationService = organizationService;
        this.userService = userService;
        this.departmentUserService = departmentUserService;
        this.positionService = positionService;
    }

    @Override
    public DepartmentDto create(CreateDepartmentRequest request) {
        OrganizationDto organizationDto = organizationService.findById(request.getOrganizationId());
        Department department;

        if (request.getHeadUserId() == null) {
            department = Department
                    .builder()
                    .organization(OrganizationMapper.INSTANCE.toEntity(organizationDto))
                    .departmentName(request.getDepartmentName())
                    .isDeleted(false)
                    .build();
        } else {
            UserDto userDto = userService.findById(request.getHeadUserId());
            department = Department
                    .builder()
                    .organization(OrganizationMapper.INSTANCE.toEntity(organizationDto))
                    .departmentName(request.getDepartmentName())
                    .head(UserMapper.INSTANCE.toEntity(userDto))  // todo: setHead ?
                    .isDeleted(false)
                    .build();
        }
        return DepartmentMapper.INSTANCE.toDto
                (departmentRepository.save(department));
    }

    @Override
    public DepartmentDto setHead(SetHeadRequest request) {
        UserDto userDto =  userService.findById(request.getHeadId());
        return departmentRepository.findByIdAndIsDeletedFalse(request.getDepartmentId())
                .map(department -> {
                    department.setHead(UserMapper.INSTANCE.toEntity(userDto));
                    departmentRepository.save(department);
                    return DepartmentMapper.INSTANCE.toDto(department);
                }).orElseThrow(() -> new EntityNotFoundException
                        ("Department with id=" + request.getDepartmentId() + " not found"));
    }

    @Override
    public DepartmentDto findById(Long id) {
        return DepartmentMapper.INSTANCE.toDto
                (departmentRepository.findByIdAndIsDeletedFalse(id)
                        .orElseThrow(() -> new EntityNotFoundException
                                ("Department with id=" + id + " not found")));
    }

    @Override
    public List<DepartmentDto> findAll() {
        return DepartmentMapper.INSTANCE.toDtoList
                (departmentRepository.findAll());
    }

    @Override
    public List<DepartmentDto> findAllByOrganizationId(Long organizationId) {
        organizationService.findById(organizationId);
        return DepartmentMapper.INSTANCE.toDtoList
                (departmentRepository.findAllByOrganizationId(organizationId));
    }

    @Override
    public DepartmentDto update(UpdateDepartmentRequest request) {
        UserDto userDto = userService.findById(request.getHeadId());
        return departmentRepository.findByIdAndIsDeletedFalse(request.getDepartmentId())
                .map(department -> {
                    department.setDepartmentName(request.getDepartmentName());
                    department.setHead(UserMapper.INSTANCE.toEntity(userDto));
                    departmentRepository.save(department);

                return DepartmentMapper.INSTANCE.toDto(department);
        }).orElseThrow(() -> new EntityNotFoundException
                        ("Department with id=" + request.getDepartmentId() + " not found"));
    }

    public void delete(Long id) {
        departmentRepository.findByIdAndIsDeletedFalse(id)
                .map(department -> {
                    department.setIsDeleted(true);
                    return departmentRepository.save(department);
                }).orElseThrow(() -> new EntityNotFoundException
                        ("Department with id=" + id + " not found"));
    }

    @Override
    @Transactional
    public MessageResponse deleteAndMoveMembersToAnotherDep(Long oldDepartmentId, Long newDepartmentId) {
        delete(oldDepartmentId);
        departmentUserService.changeDepartment(oldDepartmentId, newDepartmentId);
        //position change
        positionService.changeDepartmentInPosition(oldDepartmentId, newDepartmentId);
        return MessageResponse.of("Department with id=" + oldDepartmentId + " is deleted, " +
                "and its members have been moved to department with id=" + newDepartmentId);
    }

    @Override
    @Transactional
    public MessageResponse deleteDepAndMembers(Long id) {
        delete(id);
        List<Long> userIds = departmentUserService.findAllUserIdsByDepartmentId(id);
        for (Long userId : userIds) {
            userService.delete(userId);
        }
        positionService.deletePositionsByDepartmentId(id);
        return MessageResponse.of("Department with id=" + id + " and its members are deleted");
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        return null;
    }
}
