package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.OrganizationUserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizationUserService {

    OrganizationUserDto findById(Long id);
    List<Long> findByUserId(Long userId);
    List<Long> findAllUserIdsByOrganizationId(Long organizationId);
//    OrganizationUserDto setOrganization(Long id);
    List<Long> findOrganizationIdByUserId(Long id);
    void save(OrganizationUserDto organizationUserDto);
    void changeOrganization(Long userId, Long currentOrganizationId, Long newOrganizationId);


}
