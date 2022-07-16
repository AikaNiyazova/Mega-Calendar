package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.request.CreateOrganizationRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface OrganizationService {

    OrganizationDto create(CreateOrganizationRequest request);

    OrganizationDto setAdmin(OrganizationDto organizationDto);

    OrganizationDto findById(Long id);

    OrganizationDto update(OrganizationDto organizationDto);

    MessageResponse delete(Long id);

    OrganizationDto save(OrganizationDto organizationDto);

}
