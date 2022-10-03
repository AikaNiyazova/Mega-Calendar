package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.MeetingDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MeetingDateTimeRepository extends JpaRepository<MeetingDateTime, Long> {

    @Query(value = "SELECT meeting_id FROM tb_meeting_date_time " +
            "WHERE id = ?", nativeQuery = true)
    Long findMeetingIdById(Long id);

    @Query(value = "SELECT * FROM tb_meeting_dates " +
            "WHERE meeting_id = ?1", nativeQuery = true)
    List<MeetingDateTime> findDatesByMeetingId(Long meetingId);

    @Query(value = "SELECT md.* FROM tb_meeting_date_time md " +
            "JOIN tb_meeting m on m.id = md.meeting_id " +
            "JOIN tb_meeting_user mu on m.id = mu.meeting_id " +
            "WHERE mu.user_id = ?1 " +
            "AND md.is_deleted = false " +
            "AND mu.status IN ('ACCEPTED', 'MODIFIED') " +
            "AND md.meeting_date BETWEEN ?2 AND ?3", nativeQuery = true)
    List<MeetingDateTime> findAllByUserIdAndDates(Long userId, LocalDate startDate, LocalDate endDate);

    @Query(value = "SELECT md.* FROM tb_meeting_date_time md " +
            "JOIN tb_meeting m on m.id = md.meeting_id " +
            "WHERE m.room_id = ?1 " +
            "AND md.is_deleted = false " +
            "AND md.meeting_date BETWEEN ?2 AND ?3", nativeQuery = true)
    List<MeetingDateTime> findAllByRoomIdAndDates(Long roomId, LocalDate startDate, LocalDate endDate);

    @Modifying
    @Query(value = "UPDATE tb_meeting_date_time " +
            "SET is_deleted = true " +
            "WHERE id = ?1", nativeQuery = true)
    void delete(Long id);
}
