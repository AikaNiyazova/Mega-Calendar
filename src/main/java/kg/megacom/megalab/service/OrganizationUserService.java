package kg.megacom.megalab.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrganizationUserService {

    List<Long> findAllUserIdsByOrganizationId(Long organizationId);

}
