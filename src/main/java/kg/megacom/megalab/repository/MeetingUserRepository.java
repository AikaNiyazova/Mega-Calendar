package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.MeetingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
