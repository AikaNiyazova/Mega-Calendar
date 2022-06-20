package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.entity.HiddenRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiddenRoomRepository extends JpaRepository<HiddenRoom, Long> {
}
