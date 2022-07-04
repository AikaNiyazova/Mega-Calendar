package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.service.PositionUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionUserServiceImpl implements PositionUserService {


    @Override
    public List<Long> findAllUserIdsByPositionId(Long organizationId) {
        return null;
    }

}
