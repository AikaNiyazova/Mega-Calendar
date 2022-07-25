package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.MeetingDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MeetingDatesRepository extends JpaRepository<MeetingDates, Long> {

    @Query(value = "SELECT * FROM tb_meeting_dates " +
            "WHERE meeting_id = ?1", nativeQuery = true)
    List<MeetingDates> findDatesByMeetingId(Long meetingId);

    @Modifying
    @Query(value = "DELETE FROM tb_meeting_dates " +
            "WHERE meeting_id = ?1", nativeQuery = true)
    void deleteByMeetingId(Long meetingId);
}
