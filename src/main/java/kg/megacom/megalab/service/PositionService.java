package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.PositionDto;
import kg.megacom.megalab.model.request.CreatePositionRequest;
import org.springframework.stereotype.Service;

@Service
public interface PositionService {

    PositionDto create(CreatePositionRequest request);

    PositionDto findById(Long id);

    PositionDto update(PositionDto positionDto);

    void delete(Long id);

    void changeDepartmentInPosition(Long oldDepartmentId, Long newDepartmentId);

    void deletePositionsByDepartmentId(Long departmentId);

    PositionDto save(PositionDto positionDto);

}
