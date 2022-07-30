package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.request.CreateDepartmentRequest;
import kg.megacom.megalab.model.request.SetHeadRequest;
import kg.megacom.megalab.model.request.UpdateDepartmentRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {

    DepartmentDto create(CreateDepartmentRequest request);

    DepartmentDto setHead(SetHeadRequest request);

    DepartmentDto findById(Long id);

    List<DepartmentDto> findAll();

    List<DepartmentDto> findAllByOrganizationId(Long organizationId);

    DepartmentDto update(UpdateDepartmentRequest request);

    MessageResponse deleteAndMoveMembersToAnotherDep(Long oldDepartmentId, Long newDepartmentId);

    MessageResponse deleteDepAndMembers(Long id);

    DepartmentDto save(DepartmentDto departmentDto);

}
