package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.PositionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionUserRepository extends JpaRepository<PositionUser, Long> {
}
