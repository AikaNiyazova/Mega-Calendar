package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Department;
import kg.megacom.megalab.model.mapper.DepartmentMapper;
import kg.megacom.megalab.model.mapper.OrganizationMapper;
import kg.megacom.megalab.model.mapper.UserMapper;
import kg.megacom.megalab.model.request.CreateDepartmentRequest;
import kg.megacom.megalab.repository.DepartmentRepository;
import kg.megacom.megalab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

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
        Department department = Department
                .builder()
                .organization(OrganizationMapper.INSTANCE.toEntity(organizationDto))
                .departmentName(request.getDepartmentName())
//                .head(request.getHeadUserId())  // setHead ?
                .isDeleted(false)
                .build();
        return DepartmentMapper.INSTANCE.toDto
                (departmentRepository.save(department));
    }

    @Override
    public DepartmentDto findById(Long id) {
        return DepartmentMapper.INSTANCE.toDto
                (departmentRepository.findByIdAndIsDeletedFalse(id)
                        .orElseThrow(() -> new EntityNotFoundException
                                ("Department with id=" + id + " not found")));
    }

    @Override
    public DepartmentDto update(DepartmentDto departmentDto) {
        UserDto userDto = userService.findById(departmentDto.getHead().getId());
        return departmentRepository.findByIdAndIsDeletedFalse(departmentDto.getId())
                .map(department -> {
                    department.setDepartmentName(departmentDto.getDepartmentName());
                    department.setHead(UserMapper.INSTANCE.toEntity(userDto));
                    departmentRepository.save(department);

                return DepartmentMapper.INSTANCE.toDto(department);
        }).orElseThrow(() -> new EntityNotFoundException
                        ("Department with id=" + departmentDto.getId() + " not found"));
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
    public void deleteAndMoveMembersToAnotherDep(Long oldDepartmentId, Long newDepartmentId) {
        delete(oldDepartmentId);
        departmentUserService.changeDepartment(oldDepartmentId, newDepartmentId);
        //position change
        positionService.changeDepartmentInPosition(oldDepartmentId, newDepartmentId);
    }

    @Override
    public void deleteDepAndMembers(Long id) {
        delete(id);
//        //users delete (JOIN UPDATE)
//        List<Long> userIds = departmentUserService.findAllUserIdsByDepartmentId(id);
//        for (Long userId : userIds) {
//            userService.delete(userId);
//        }
//        //positions delete
//        positionService.deletePositionsByDepartmentId(id);
        //users + depPositions delete
        userService.deleteUsersAndPositions(id); //todo: ???
    }

    @Override
    public DepartmentDto save(DepartmentDto departmentDto) {
        return null;
    }
}
