package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.MeetingDateTimeDto;
import kg.megacom.megalab.model.dto.MeetingUserDto;
import kg.megacom.megalab.model.dto.NotificationDto;
import kg.megacom.megalab.model.entity.MeetingDateTime;
import kg.megacom.megalab.model.entity.MeetingUser;
import kg.megacom.megalab.model.entity.Notification;
import kg.megacom.megalab.model.mapper.MeetingDateTimeMapper;
import kg.megacom.megalab.model.mapper.MeetingUserMapper;
import kg.megacom.megalab.model.mapper.NotificationMapper;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.NotificationRepository;
import kg.megacom.megalab.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendToParticipant(MeetingDateTimeDto meetingDateTimeDto, MeetingUserDto meetingUserDto) {
        MeetingUser meetingUser = MeetingUserMapper.INSTANCE.toEntity(meetingUserDto);
        MeetingDateTime meetingDateTime = MeetingDateTimeMapper.INSTANCE.toEntity(meetingDateTimeDto);
        notificationRepository.save(
                Notification.builder()
                        .sendFrom(meetingUser.getMeeting().getMeetingAuthor())
                        .sendTo(meetingUser.getUser())
                        .meetingDateTime(meetingDateTime)
                        .sendAt(LocalDateTime.now())
                        .status(meetingUserDto.getStatus())
                        .isRead(Boolean.FALSE)
                        .build()
        );
        MessageResponse.of("Notification is sent");
    }

    @Override
    public void sendToAuthor(MeetingDateTimeDto meetingDateTimeDto, MeetingUserDto meetingUserDto) {
        MeetingUser meetingUser = MeetingUserMapper.INSTANCE.toEntity(meetingUserDto);
        MeetingDateTime meetingDateTime = MeetingDateTimeMapper.INSTANCE.toEntity(meetingDateTimeDto);
        notificationRepository.save(
                Notification.builder()
                        .sendFrom(meetingUser.getUser())
                        .sendTo(meetingUser.getMeeting().getMeetingAuthor())
                        .meetingDateTime(meetingDateTime)
                        .sendAt(LocalDateTime.now())
                        .status(meetingUserDto.getStatus())
                        .isRead(Boolean.FALSE)
                        .build()
        );
        MessageResponse.of("Notification is sent");
    }

    @Override
    public List<NotificationDto> findAll() {
        return NotificationMapper.INSTANCE.toDtoList(notificationRepository.findAll());
    }

    @Override
    public List<NotificationDto> findByUserSendToId(Long userSentToId) {
        return NotificationMapper.INSTANCE.toDtoList
                (notificationRepository.findByUserSendToId(userSentToId));
    }

    @Override
    public List<NotificationDto> findByUserSendFromId(Long userSentFromId) {
        return NotificationMapper.INSTANCE.toDtoList
                (notificationRepository.findByUserSendFromId(userSentFromId));
    }
}
