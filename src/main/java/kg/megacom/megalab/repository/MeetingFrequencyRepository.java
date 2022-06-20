package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.MeetingFrequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingFrequencyRepository extends JpaRepository<MeetingFrequency, Long> {
}
