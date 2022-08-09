package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.MeetingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface MeetingUserRepository extends JpaRepository<MeetingUser, Long> {

    @Query(value = "SELECT user_id " +
            "FROM tb_meeting_user " +
            "WHERE  meeting_id = ?1", nativeQuery = true)
    List<Long> findAllUserIdsByMeetingId(Long meetingId);

    @Query(value = "SELECT * FROM tb_meeting_user " +
            "WHERE user_id = ?1 AND meeting_id = ?2", nativeQuery = true)
    MeetingUser findByUserIdAndMeetingId(Long userId, Long meetingId);

    @Query(value = "SELECT mu.* " +
            "FROM tb_meeting_user mu " +
            "JOIN tb_meeting m ON mu.meeting_id = m.id " +
            "JOIN tb_meeting_dates md ON md.meeting_id = m.id " +
            "WHERE mu.user_id = ?1 " +
            "AND md.meeting_date = ?2 " +
            "AND (?3 >= m.meeting_start_time AND ?3 < m.meeting_end_time " +
            "OR ?4 > m.meeting_start_time AND ?4 <= m.meeting_end_time) " +
            "OR (m.meeting_start_time >= ?3 AND m.meeting_start_time < ?4 " +
            "AND m.meeting_end_time > ?3 AND m.meeting_end_time <= ?4)",
            nativeQuery = true)
    List<MeetingUser> findByUserIdAndDateAndTime(Long userId, LocalDate date,
                                           LocalTime startTime, LocalTime endTime);

    @Modifying
    @Query(value = "DELETE FROM tb_meeting_user " +
            "WHERE meeting_id = ?", nativeQuery = true)
    void deleteByMeetingId(Long meetingId);

    @Modifying
    @Query(value = "DELETE FROM tb_meeting_user " +
            "WHERE user_id = ?1 AND meeting_id = ?2", nativeQuery = true)
    void deleteByUserIdAndMeetingId(Long userId, Long meetingId);

    @Modifying
    @Query(value = "DELETE FROM tb_meeting_user " +
            "WHERE delegate_user_id = ?1 AND meeting_id = ?2", nativeQuery = true)
    void deleteByDelegateIdAndMeetingId(Long delegateId, Long meetingId);

}
