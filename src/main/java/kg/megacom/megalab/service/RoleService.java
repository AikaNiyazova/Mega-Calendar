package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    RoleDto create(RoleDto roleDto);
    RoleDto findById(Long id);
    MessageResponse delete(Long id);
}
