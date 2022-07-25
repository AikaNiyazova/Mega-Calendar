package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.PositionDto;
import kg.megacom.megalab.model.request.CreatePositionRequest;
import kg.megacom.megalab.model.request.UpdatePositionRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionService {

    PositionDto create(CreatePositionRequest request);

    PositionDto findById(Long id);

    List<PositionDto> findAll();

    List<PositionDto> findAllByDepartmentId(Long departmentId);

    PositionDto update(UpdatePositionRequest request);

    MessageResponse delete(Long id);

    void changeDepartmentInPosition(Long oldDepartmentId, Long newDepartmentId);

    void deletePositionsByDepartmentId(Long departmentId);

    PositionDto save(PositionDto positionDto);

}
