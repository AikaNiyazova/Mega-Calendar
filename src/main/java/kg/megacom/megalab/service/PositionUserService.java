package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.PositionUserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionUserService {

    List<Long> findAllUserIdsByPositionId(Long organizationId);
    void changePosition(Long userId, Long oldPositionId, Long newPositionId);
    void save(PositionUserDto positionUserDto);

}
