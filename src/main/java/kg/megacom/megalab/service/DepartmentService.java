package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.request.CreateDepartmentRequest;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentService {

    DepartmentDto create(CreateDepartmentRequest request);

    DepartmentDto findById(Long id);

    DepartmentDto update(DepartmentDto departmentDto);

    void deleteAndMoveMembersToAnotherDep(Long oldDepartmentId, Long newDepartmentId);

    void deleteDepAndMembers(Long id);

    DepartmentDto save(DepartmentDto departmentDto);

}
