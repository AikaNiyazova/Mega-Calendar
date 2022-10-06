package kg.megacom.megalab.repository;

import kg.megacom.megalab.model.dto.NotificationDto;
import kg.megacom.megalab.model.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query(value = "SELECT * " +
            "FROM tb_notification n " +
//            "JOIN tb_user u " +
//            "ON n.send_to_id = u.id " +
            "WHERE n.send_to_id = ?1 ", nativeQuery = true)
    List<Notification> findByUserSendToId(Long userSentToId);

    @Query(value = "SELECT * " +
            "FROM tb_notification " +
//            "JOIN tb_user u " +
//            "ON n.send_from_id = u.id" +
            "WHERE send_from_id = ?1 ", nativeQuery = true)
    List<Notification> findByUserSendFromId(Long userSentFromId);
}
