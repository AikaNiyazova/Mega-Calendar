package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.entity.Role;
import kg.megacom.megalab.model.mapper.RoleMapper;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.RoleRepository;
import kg.megacom.megalab.service.RoleService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    public RoleServiceImpl(RoleRepository roleRepository) {

        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        Role role = Role
                .builder()
                .roleName(roleDto.getRoleName())
                .authorities(roleDto.getAuthorities())
                .isDeleted(false)
                .build();
        roleRepository.save(role);

        return RoleMapper.INSTANCE.toDto(role);
    }

    @Override
    public RoleDto findById(Long id) {
        return RoleMapper.INSTANCE
                .toDto(roleRepository.findByIdAndIsDeletedFalse(id)
                        .orElseThrow(() -> new EntityNotFoundException("Role with id=" + id + " not found.")));
    }

    @Override
    public MessageResponse delete(Long id) {
        roleRepository.findByIdAndIsDeletedFalse(id).map(role -> {
            role.setIsDeleted(true);
            return roleRepository.save(role);
        }).orElseThrow(() -> new EntityNotFoundException("Role with id = " + id + " not found"));
        return MessageResponse.of("Role with id = " + id + " is deleted");
    }
}
