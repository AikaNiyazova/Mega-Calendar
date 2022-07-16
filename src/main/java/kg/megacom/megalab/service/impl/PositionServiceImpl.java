package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.dto.PositionDto;
import kg.megacom.megalab.model.entity.Position;
import kg.megacom.megalab.model.mapper.DepartmentMapper;
import kg.megacom.megalab.model.mapper.PositionMapper;
import kg.megacom.megalab.model.request.CreatePositionRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.PositionRepository;
import kg.megacom.megalab.service.DepartmentService;
import kg.megacom.megalab.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final DepartmentService departmentService;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository,
                               DepartmentService departmentService) {
        this.positionRepository = positionRepository;
        this.departmentService = departmentService;
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
    public PositionDto update(PositionDto positionDto) {
        return positionRepository.findByIdAndIsDeletedFalse(positionDto.getId())
                .map(position -> {
                    position.setPositionName(positionDto.getPositionName());
                    positionRepository.save(position);

                return PositionMapper.INSTANCE.toDto(position);
        }).orElseThrow(() -> new EntityNotFoundException
                        ("Position with id=" + positionDto.getId() + " not found"));
    }

    @Override
    public MessageResponse delete(Long id) {
        positionRepository.findByIdAndIsDeletedFalse(id)
                .map(position -> {
                    position.setIsDeleted(true);
                    return positionRepository.save(position);
                }).orElseThrow(() -> new EntityNotFoundException
                        ("Position with id=" + id + " not found"));
        return MessageResponse.of("Position with id=" + id + " is deleted");
        //todo: if position.isDeleted = true { sout("no position") }
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
