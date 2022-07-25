package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.Organization;
import kg.megacom.megalab.model.mapper.OrganizationMapper;
import kg.megacom.megalab.model.mapper.UserMapper;
import kg.megacom.megalab.model.request.CreateOrganizationRequest;
import kg.megacom.megalab.model.request.SetAdminRequest;
import kg.megacom.megalab.model.request.UpdateOrganizationRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.OrganizationRepository;
import kg.megacom.megalab.service.OrganizationService;
import kg.megacom.megalab.service.OrganizationUserService;
import kg.megacom.megalab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationUserService organizationUserService;
    private final UserService userService;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository,
                                   OrganizationUserService organizationUserService,
                                   UserService userService) {
        this.organizationRepository = organizationRepository;
        this.organizationUserService = organizationUserService;
        this.userService = userService;
    }

    @Override
    public OrganizationDto create(CreateOrganizationRequest request) {
        Organization organization;

        if (request.getAdminUserId() == null) {
            organization = Organization
                    .builder()
                    .organizationName(request.getOrganizationName())
                    .isDeleted(false)
                    .build();
        } else {
            UserDto userDto = userService.findById(request.getAdminUserId());
            organization = Organization
                    .builder()
                    .organizationName(request.getOrganizationName())
                    .admin(UserMapper.INSTANCE.toEntity(userDto))  // todo: setAdmin ?
                    .isDeleted(false)
                    .build();
        }

        return OrganizationMapper.INSTANCE.toDto
                (organizationRepository.save(organization));
    }

    @Override
    public OrganizationDto setAdmin(SetAdminRequest request) {
        UserDto userDto =  userService.findById(request.getAdminId());
        return organizationRepository.findByIdAndIsDeletedFalse(request.getOrganizationId())
                .map(organization -> {
                    organization.setAdmin(UserMapper.INSTANCE.toEntity(userDto));
                    organizationRepository.save(organization);
                return OrganizationMapper.INSTANCE.toDto(organization);
                }).orElseThrow(() -> new EntityNotFoundException
                        ("Organization with id=" + request.getOrganizationId() + " not found"));
    }

    @Override
    public OrganizationDto findById(Long id) {
        return OrganizationMapper.INSTANCE.toDto
                (organizationRepository.findByIdAndIsDeletedFalse(id)
                        .orElseThrow(() -> new EntityNotFoundException
                                ("Organization with id=" + id + " not found")));
    }

    @Override
    public List<OrganizationDto> findAll() {
        return OrganizationMapper.INSTANCE.toDtoList
                (organizationRepository.findAll());
    }

    @Override
    public OrganizationDto update(UpdateOrganizationRequest request) {
        UserDto userDto =  userService.findById(request.getAdminId());
        return organizationRepository.findByIdAndIsDeletedFalse(request.getOrganizationId())
                .map(organization -> {
                    organization.setOrganizationName(request.getOrganizationName());
                    organization.setAdmin(UserMapper.INSTANCE.toEntity(userDto));
                    organizationRepository.save(organization);

                return OrganizationMapper.INSTANCE.toDto(organization);
                }).orElseThrow(() -> new EntityNotFoundException
                        ("Organization with id=" + request.getOrganizationId() + " not found"));
    }

    @Override
    @Transactional
    public MessageResponse delete(Long id) {
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
        return MessageResponse.of("Organization with id=" + id + " and its related entities are deleted");
    }

    @Override
    public OrganizationDto save(OrganizationDto organizationDto) {
        return null;
    }
}
