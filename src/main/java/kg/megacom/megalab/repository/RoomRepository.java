package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> findByIdAndIsDeletedFalse(Long aLong);

    @Query(value = "SELECT * FROM tb_room WHERE is_deleted = false", nativeQuery = true)
    List<Room> findAllAndIsDeletedFalse();

    @Query(value = "SELECT * FROM tb_room r " +
            "LEFT JOIN tb_hidden_room hr ON r.id = hr.room_id " +
            "WHERE ? NOT BETWEEN hr.hiding_start_date AND hr.hiding_end_date " +
            "OR hr.room_id IS NULL " +
            "AND r.is_deleted = false ", nativeQuery = true)
    List<Room> findAllNotHiddenForDate(LocalDate date);

    Room findByRoomName(String name);

    @Query(value = "SELECT * FROM tb_room " +
            "WHERE is_deleted = false " +
            "AND room_name ILIKE '%?%' ", nativeQuery = true)
    List<Room> findAllByRoomName(String roomName);

    @Modifying
    @Query("UPDATE Room r SET r.isDeleted=true WHERE r.id=?1")
    void deleteRoomById(Long id);

    @Query(value = "SELECT DISTINCT r.* FROM tb_room r " +
//            "LEFT JOIN tb_meeting m ON r.id = m.room_id " +
            "LEFT JOIN tb_meeting_date_time md on r.id = md.room_id " +
            "WHERE md.is_deleted = false " +
            "AND md.meeting_date = ?1 " +
            "AND ((?2 < md.meeting_start_time OR ?2 >= md.meeting_end_time) " +
            "AND (?3 <= md.meeting_start_time OR ?3 > md.meeting_end_time)) " +
            "AND ((md.meeting_start_time < ?2 OR md.meeting_start_time >= ?3) " +
            "AND (md.meeting_end_time <= ?2 OR md.meeting_end_time > ?3)) " +
            "OR md.meeting_date IS NULL",
            nativeQuery = true)
    List<Room> findFreeRoomsForDateAndTime(LocalDate date, LocalTime startTime, LocalTime endTime); //todo
}
