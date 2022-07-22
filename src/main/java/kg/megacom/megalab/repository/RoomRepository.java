package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomName(String name);

    @Modifying
    @Query("UPDATE Room r SET r.isDeleted=true WHERE r.id=?1")
    void deleteRoomById(Long id);

}
