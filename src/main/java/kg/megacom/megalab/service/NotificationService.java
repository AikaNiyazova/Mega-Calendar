package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.MeetingDateTimeDto;
import kg.megacom.megalab.model.dto.MeetingUserDto;
import kg.megacom.megalab.model.dto.NotificationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    void sendToParticipant(MeetingDateTimeDto meetingResponse, MeetingUserDto meetingUserDto);
    void sendToAuthor(MeetingDateTimeDto meetingDateTimeDto, MeetingUserDto meetingUserDto);
    List<NotificationDto> findAll();
    List<NotificationDto> findByUserSendToId(Long userSentToId);
    List<NotificationDto> findByUserSendFromId(Long userSentFromId);
}
