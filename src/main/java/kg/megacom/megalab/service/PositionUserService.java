package kg.megacom.megalab.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PositionUserService {

    List<Long> findAllUserIdsByPositionId(Long organizationId);

}
