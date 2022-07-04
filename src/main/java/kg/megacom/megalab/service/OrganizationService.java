package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.request.CreateOrganizationRequest;
import org.springframework.stereotype.Service;

@Service
public interface OrganizationService {

    OrganizationDto create(CreateOrganizationRequest request);

    OrganizationDto findById(Long id);

    OrganizationDto update(OrganizationDto organizationDto);

    void delete(Long id);

    OrganizationDto save(OrganizationDto organizationDto);

}
