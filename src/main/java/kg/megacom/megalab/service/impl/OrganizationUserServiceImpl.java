package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.OrganizationUserDto;
import kg.megacom.megalab.model.entity.OrganizationUser;
import kg.megacom.megalab.model.mapper.OrganizationUserMapper;
import kg.megacom.megalab.repository.OrganizationUserRepository;
import kg.megacom.megalab.service.OrganizationService;
import kg.megacom.megalab.service.OrganizationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrganizationUserServiceImpl implements OrganizationUserService {

    private final OrganizationUserRepository organizationUserRepository;

    @Autowired
    public OrganizationUserServiceImpl(OrganizationUserRepository organizationUserRepository) {
        this.organizationUserRepository = organizationUserRepository;
    }

    @Override
    public OrganizationUserDto findById(Long id) {
        OrganizationUser organizationUser = organizationUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("OrganizationUser with id=" + id + " not found"));
        return OrganizationUserMapper.INSTANCE.toDto(organizationUser);
    }

    @Override
    public List<Long> findByUserId(Long userId) {
        return organizationUserRepository.findByUserId(userId);
    }

    @Override
    public List<Long> findAllUserIdsByOrganizationId(Long organizationId) {
        return organizationUserRepository.findAllUserIdsByOrganizationId(organizationId);
    }

    @Override
    public List<Long> findOrganizationIdByUserId(Long id) {
        return organizationUserRepository.findOrganizationIdByUserId(id);
    }

    @Override
    public void save(OrganizationUserDto organizationUserDto) {
        organizationUserRepository.save(OrganizationUserMapper.INSTANCE.toEntity(organizationUserDto));
    }

    @Override
    public void changeOrganization(Long userId, Long oldOrganizationId, Long newOrganizationId) {
        organizationUserRepository.changeOrganization(userId, oldOrganizationId, newOrganizationId);
    }
}
