package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.HiddenRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HiddenRoomRepository extends JpaRepository<HiddenRoom, Long> {

    @Query(value = "SELECT * FROM tb_hidden_room " +
            "WHERE now() BETWEEN hiding_start_date " +
            "AND hiding_end_date", nativeQuery = true)
    List<HiddenRoom> findAllCurrent();
}
