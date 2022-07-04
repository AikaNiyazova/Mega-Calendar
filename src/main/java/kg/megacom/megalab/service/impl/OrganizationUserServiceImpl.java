package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.repository.OrganizationUserRepository;
import kg.megacom.megalab.service.OrganizationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationUserServiceImpl implements OrganizationUserService {

    private final OrganizationUserRepository organizationUserRepository;

    @Autowired
    public OrganizationUserServiceImpl(OrganizationUserRepository organizationUserRepository) {
        this.organizationUserRepository = organizationUserRepository;
    }

    @Override
    public List<Long> findAllUserIdsByOrganizationId(Long organizationId) {
        return organizationUserRepository.findAllUserIdsByOrganizationId(organizationId);
    }
}
