package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.Position;
import kg.megacom.megalab.model.entity.PositionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionUserRepository extends JpaRepository<PositionUser, Long> {

    @Query(value = "SELECT user_id FROM tb_position_user " +
            "WHERE position_id = ?1 " +
            "GROUP BY user_id " +
            "HAVING count(user_id) = 1", nativeQuery = true)
    List<Long> findAllUserIdsByPositionId(Long id); //todo:

}
