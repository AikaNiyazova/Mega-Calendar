package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    @Query(value = "SELECT room_id FROM tb_meeting m " +
            "JOIN tb_meeting_dates md on m.id = md.meeting_id " +
            "WHERE md.meeting_date = ?1 " +
            "AND ?2 NOT BETWEEN m.meeting_start_time AND m.meeting_end_time " +
            "AND ?3 NOT BETWEEN m.meeting_start_time AND m.meeting_end_time", nativeQuery = true)
    void dfknv(); //todo

    @Query(value = "SELECT * FROM tb_meeting m " +
            "JOIN tb_meeting_user mu on m.id = mu.meeting_id " +
            "JOIN tb_meeting_dates md on m.id = md.meeting_id " +
            "WHERE mu.user_id = ?1 " +
            "AND md.meeting_date = ?2", nativeQuery = true)
    List<Meeting> findAllByUserIdAndDate(Long userId, LocalDate date);

    @Query(value = "SELECT * FROM tb_meeting m " +
            "JOIN tb_meeting_user mu on m.id = mu.meeting_id " +
            "JOIN tb_meeting_dates md on m.id = md.meeting_id " +
            "WHERE mu.user_id = ?1 " +
            "AND md.meeting_date BETWEEN ?2 AND ?3", nativeQuery = true)
    List<Meeting> findAllByUserIdAndTwoDates(Long userId, LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT * FROM tb_meeting m " +
            "JOIN tb_meeting_dates md on m.id = md.meeting_id " +
            "WHERE m.room_id = ?1 " +
            "AND md.meeting_date = ?2", nativeQuery = true)
    List<Meeting> findAllByRoomIdAndDate(Long roomId, LocalDate date);

    @Query(value = "SELECT * FROM tb_meeting m " +
            "JOIN tb_meeting_dates md on m.id = md.meeting_id " +
            "WHERE m.room_id = ?1 " +
            "AND md.meeting_date BETWEEN ?2 AND ?3", nativeQuery = true)
    List<Meeting> findAllByRoomIdAndTwoDates(Long roomId, LocalDate startDate, LocalDate endDate);

}
