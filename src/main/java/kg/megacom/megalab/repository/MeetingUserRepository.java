package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.MeetingUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingUserRepository extends JpaRepository<MeetingUser, Long> {
}
