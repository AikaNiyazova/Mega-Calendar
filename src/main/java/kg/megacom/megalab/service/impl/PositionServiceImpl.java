package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.dto.PositionDto;
import kg.megacom.megalab.model.entity.Position;
import kg.megacom.megalab.model.mapper.DepartmentMapper;
import kg.megacom.megalab.model.mapper.PositionMapper;
import kg.megacom.megalab.model.request.CreatePositionRequest;
import kg.megacom.megalab.model.request.UpdatePositionRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.PositionRepository;
import kg.megacom.megalab.service.DepartmentService;
import kg.megacom.megalab.service.PositionService;
import kg.megacom.megalab.service.PositionUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final DepartmentService departmentService;
    private final PositionUserService positionUserService;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository,
                               DepartmentService departmentService,
                               PositionUserService positionUserService) {
        this.positionRepository = positionRepository;
        this.departmentService = departmentService;
        this.positionUserService = positionUserService;
    }

    @Override
    public PositionDto create(CreatePositionRequest request) {
        DepartmentDto departmentDto = departmentService.findById(request.getDepartmentId());
        Position position = Position
                .builder()
                .department(DepartmentMapper.INSTANCE.toEntity(departmentDto))
                .positionName(request.getPositionName())
                .isDeleted(false)
                .build();
        return PositionMapper.INSTANCE.toDto
                (positionRepository.save(position));
    }

    @Override
    public PositionDto findById(Long id) {
        return PositionMapper.INSTANCE.toDto
                (positionRepository.findByIdAndIsDeletedFalse(id)
                        .orElseThrow(() -> new EntityNotFoundException
                                ("Position with id=" + id + " not found")));
    }

    @Override
    public List<PositionDto> findAll() {
        return PositionMapper.INSTANCE.toDtoList
                (positionRepository.findAll());
    }

    @Override
    public List<PositionDto> findAllByDepartmentId(Long departmentId) {
        departmentService.findById(departmentId);
        return PositionMapper.INSTANCE.toDtoList
                (positionRepository.findAllByDepartmentId(departmentId));
    }

    @Override
    public PositionDto update(UpdatePositionRequest request) {
        return positionRepository.findByIdAndIsDeletedFalse(request.getPositionId())
                .map(position -> {
                    position.setPositionName(request.getPositionName());
                    positionRepository.save(position);

                return PositionMapper.INSTANCE.toDto(position);
        }).orElseThrow(() -> new EntityNotFoundException
                        ("Position with id=" + request.getPositionId() + " not found"));
    }

    @Override
    public MessageResponse delete(Long id) {
        positionUserService.setNullToPositionId(id);
        positionRepository.findByIdAndIsDeletedFalse(id)
                .map(position -> {
                    position.setIsDeleted(true);
                    return positionRepository.save(position);
                }).orElseThrow(() -> new EntityNotFoundException
                        ("Position with id=" + id + " not found"));
        return MessageResponse.of("Position with id=" + id + " is deleted");
//  todo: if position.isDeleted = true { sout("no position") }
//  todo:positionUserService.findByPositionId(set 0 maybe) ?
    }

    @Override
    public void changeDepartmentInPosition(Long oldDepartmentId, Long newDepartmentId) {
        positionRepository.changeDepartmentInPosition(oldDepartmentId, newDepartmentId);
    }

    @Override
    public void deletePositionsByDepartmentId(Long departmentId) {
        positionRepository.deletePositionsByDepartmentId(departmentId);
    }

    @Override
    public PositionDto save(PositionDto positionDto) {
        return null;
    }
}
