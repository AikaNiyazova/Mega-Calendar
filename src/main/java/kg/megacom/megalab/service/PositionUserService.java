package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.PositionUserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionUserService {

    List<Long> findAllUserIdsByPositionId(Long organizationId);
    void changePosition(Long userId, Long oldPositionId, Long newPositionId);
    void setNullToPositionId(Long positionId);
//    void setPositionToAllWithNull(Long positionId); //todo: do we need this?
    void save(PositionUserDto positionUserDto);

}
