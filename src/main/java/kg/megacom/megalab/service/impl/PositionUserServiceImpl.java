package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.PositionUserDto;
import kg.megacom.megalab.model.mapper.PositionUserMapper;
import kg.megacom.megalab.repository.PositionUserRepository;
import kg.megacom.megalab.service.PositionUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionUserServiceImpl implements PositionUserService {

    private final PositionUserRepository positionUserRepository;

    public PositionUserServiceImpl(PositionUserRepository positionUserRepository) {
        this.positionUserRepository = positionUserRepository;
    }

    @Override
    public List<Long> findAllUserIdsByPositionId(Long organizationId) {
        return null;
    }

    @Override
    public void changePosition(Long userId, Long oldPositionId, Long newPositionId) {
      positionUserRepository.changePosition(userId, oldPositionId, newPositionId);
    }

    @Override
    public void setNullToPositionId(Long positionId) {
        positionUserRepository.setNullToPositionId(positionId);
    }

    @Override
    public void save(PositionUserDto positionUserDto) {
        positionUserRepository.save(PositionUserMapper.INSTANCE.toEntity(positionUserDto));

    }
}
