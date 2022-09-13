package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Room findByRoomName(String name);

//    @Modifying
//    @Query("UPDATE Room r SET r.isDeleted=true WHERE r.id=?1")
//    void deleteRoomById(Long id);

    @Query(value = "SELECT DISTINCT r.* FROM tb_room r " +
            "JOIN tb_meeting m ON r.id = m.room_id " +
            "JOIN tb_meeting_dates md on m.id = md.meeting_id " +
            "WHERE md.meeting_date = ?1 " +
            "AND (?2 < m.meeting_start_time OR ?2 >= m.meeting_end_time " +
            "AND ?3 <= m.meeting_start_time OR ?3 > m.meeting_end_time) " +
            "AND (m.meeting_start_time < ?2 OR m.meeting_start_time >= ?3 " +
            "AND m.meeting_end_time <= ?2 OR m.meeting_end_time > ?3)",
            nativeQuery = true)
    List<Room> findFreeRoomsForDateAndTime(LocalDate date, LocalTime startTime, LocalTime endTime); //todo
}
