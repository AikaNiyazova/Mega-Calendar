package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.entity.Meeting;
import kg.megacom.megalab.model.entity.MeetingUser;
import kg.megacom.megalab.model.entity.Notification;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.NotificationRepository;
import kg.megacom.megalab.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public MessageResponse send(Meeting meeting, MeetingUser meetingUser) {
        notificationRepository.save(
                Notification.builder()
                        .sendFrom(meeting.getMeetingAuthor())
                        .sendTo(meetingUser.getUser())
                        .message(meeting)
                        .sendAt(LocalDateTime.now())
                        .isRead(Boolean.FALSE)
                        .build()
        );
        return MessageResponse.of("Notification is sent");
    }
}
