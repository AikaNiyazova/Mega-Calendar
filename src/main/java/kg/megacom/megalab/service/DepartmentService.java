package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.request.CreateDepartmentRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

    DepartmentDto create(CreateDepartmentRequest request);

    DepartmentDto setHead(DepartmentDto departmentDto);

    DepartmentDto findById(Long id);

    DepartmentDto update(DepartmentDto departmentDto);

    MessageResponse deleteAndMoveMembersToAnotherDep(Long oldDepartmentId, Long newDepartmentId);

    MessageResponse deleteDepAndMembers(Long id);

    DepartmentDto save(DepartmentDto departmentDto);

}
