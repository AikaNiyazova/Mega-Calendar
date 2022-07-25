package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.OrganizationUser;
import kg.megacom.megalab.model.entity.Position;
import kg.megacom.megalab.model.entity.PositionUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PositionUserRepository extends JpaRepository<PositionUser, Long> {

    @Query(value = "SELECT user_id FROM tb_position_user " +
            "WHERE position_id = ?1 " +
            "GROUP BY user_id " +
            "HAVING count(user_id) = 1", nativeQuery = true)
    List<Long> findAllUserIdsByPositionId(Long id); //todo:

    @Modifying
    @Transactional
    @Query(value = "UPDATE tb_position_user " +
            "SET position_id = ?3 " +
            "WHERE user_id =?1 " +
            "AND position_id = ?2 ", nativeQuery = true)
    void changePosition(Long userId, Long oldPositionId, Long newPositionId);

}
