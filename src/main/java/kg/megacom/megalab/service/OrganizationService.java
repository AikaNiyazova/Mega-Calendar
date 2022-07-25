package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.request.CreateOrganizationRequest;
import kg.megacom.megalab.model.request.SetAdminRequest;
import kg.megacom.megalab.model.request.UpdateOrganizationRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizationService {

    OrganizationDto create(CreateOrganizationRequest request);

    OrganizationDto setAdmin(SetAdminRequest request);

    OrganizationDto findById(Long id);

    List<OrganizationDto> findAll();

    OrganizationDto update(UpdateOrganizationRequest request);

    MessageResponse delete(Long id);

    OrganizationDto save(OrganizationDto organizationDto);

}
