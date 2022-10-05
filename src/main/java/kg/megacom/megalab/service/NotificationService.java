package kg.megacom.megalab.service;

import kg.megacom.megalab.model.entity.Meeting;
import kg.megacom.megalab.model.entity.MeetingUser;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;
@Service
public interface NotificationService {
    MessageResponse send(Meeting meeting, MeetingUser meetingUser);
}
