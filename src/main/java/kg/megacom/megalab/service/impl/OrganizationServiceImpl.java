package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Organization;
import kg.megacom.megalab.model.mapper.OrganizationMapper;
import kg.megacom.megalab.model.mapper.UserMapper;
import kg.megacom.megalab.model.request.CreateOrganizationRequest;
import kg.megacom.megalab.repository.OrganizationRepository;
import kg.megacom.megalab.service.OrganizationService;
import kg.megacom.megalab.service.OrganizationUserService;
import kg.megacom.megalab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationUserService organizationUserService;
    private final UserService userService;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationUserService organizationUserService, UserService userService) {
        this.organizationRepository = organizationRepository;
        this.organizationUserService = organizationUserService;
        this.userService = userService;
    }

    @Override
    public OrganizationDto create(CreateOrganizationRequest request) {
        Organization organization = Organization
                .builder()
                .organizationName(request.getOrganizationName())
//                .admin(request.getAdminUserId())  // setAdmin ?
                .isDeleted(false)
                .build();
        return OrganizationMapper.INSTANCE.toDto
                (organizationRepository.save(organization));
    }

    @Override
    public OrganizationDto findById(Long id) {
        return OrganizationMapper.INSTANCE.toDto
                (organizationRepository.findByIdAndIsDeletedFalse(id)
                        .orElseThrow(() -> new EntityNotFoundException
                                ("Organization with id=" + id + " not found")));
    }

    @Override
    public OrganizationDto update(OrganizationDto organizationDto) {
        UserDto userDto =  userService.findById(organizationDto.getAdmin().getId());
        return organizationRepository.findByIdAndIsDeletedFalse(organizationDto.getId())
                .map(organization -> {
                    organization.setOrganizationName(organizationDto.getOrganizationName());
                    organization.setAdmin(UserMapper.INSTANCE.toEntity(userDto));
                    organizationRepository.save(organization);

                return OrganizationMapper.INSTANCE.toDto(organization);
        }).orElseThrow(() -> new EntityNotFoundException
                        ("Organization with id=" + organizationDto.getId() + " not found"));
    }

    @Override
    public void delete(Long id) {
        Organization organization = organizationRepository.findByIdAndIsDeletedFalse(id)
                .map(org -> {
                    org.setIsDeleted(true);
                    return organizationRepository.save(org);
                }).orElseThrow(() -> new EntityNotFoundException
                        ("Organization with id=" + id + " not found"));
        //delete users from this org
        List<Long> userIds = organizationUserService.findAllUserIdsByOrganizationId(id);
        for (Long userId : userIds) {
            userService.delete(userId);
        }
        //deleted Org should not be shown in User's profile
        //delete Deps and Pos related to the Org (JOIN request)
        organizationRepository.deleteOrganizationAndRelatedEntities(id);
    }

    @Override
    public OrganizationDto save(OrganizationDto organizationDto) {
        return null;
    }
}
