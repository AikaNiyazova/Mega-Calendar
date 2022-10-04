package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.dto.MeetingUserDto;
import kg.megacom.megalab.model.entity.Label;
import kg.megacom.megalab.model.entity.MeetingUser;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.mapper.LabelMapper;
import kg.megacom.megalab.model.mapper.MeetingUserMapper;
import kg.megacom.megalab.model.mapper.UserMapper;
import kg.megacom.megalab.model.request.UpdateMeetingUserRequest;
import kg.megacom.megalab.repository.MeetingUserRepository;
import kg.megacom.megalab.service.LabelService;
import kg.megacom.megalab.service.MeetingUserService;
import kg.megacom.megalab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class MeetingUserServiceImpl implements MeetingUserService {

    private final MeetingUserRepository meetingUserRepository;
    private final LabelService labelService;
    private final UserService userService;

    @Autowired
    public MeetingUserServiceImpl(MeetingUserRepository meetingUserRepository,
                                  LabelService labelService,
                                  UserService userService) {
        this.meetingUserRepository = meetingUserRepository;
        this.labelService = labelService;
        this.userService = userService;
    }

    @Override
    public MeetingUserDto update(UpdateMeetingUserRequest request) {

        Label label = null;
        if (!(request.getLabelId() == null)) {
            label = LabelMapper.INSTANCE.toEntity(labelService.findById(request.getLabelId()));
        }

        User delegate = null;
        if (!(request.getDelegateId() == null)) {
            delegate = UserMapper.INSTANCE.toEntity(userService.findById(request.getDelegateId()));
            //todo: send notification to delegate
        }

        MeetingUser meetingUser = meetingUserRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException
                        ("MeetingUser with id=" + request.getId() + " not found"));

        meetingUser.setStatus(request.getStatus());
        meetingUser.setLabel(label);
        meetingUser.setDelegate(delegate);
        meetingUser.setReasonForRejection(request.getReasonForRejection());

        meetingUserRepository.save(meetingUser);
        return MeetingUserMapper.INSTANCE.toDto(meetingUser);
        //todo: send notification to meetingAuthor
    }

    @Override
    public List<MeetingDto> findByLabel(Long labelId) {
        return null;
    }

    @Override
    public List<MeetingDto> findByDate(LocalDate date) {
        return null;
    }

    @Override
    public List<MeetingDto> findByDates(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    @Override
    public List<Long> findAllUserIdsByMeetingId(Long meetingId) {
        return meetingUserRepository.findAllUserIdsByMeetingId(meetingId);
    }

    @Override
    public MeetingUserDto findByUserIdAndMeetingId(Long userId, Long meetingId) {
        return MeetingUserMapper.INSTANCE.toDto
                (meetingUserRepository.findByUserIdAndMeetingId(userId, meetingId));
    }

    @Override
    public void deleteByMeetingId(Long meetingId) {
        meetingUserRepository.deleteByMeetingId(meetingId);
    }

    @Override
    @Transactional
    public void deleteByUserIdAndMeetingId(Long userId, Long meetingId) {
        meetingUserRepository.deleteByUserIdAndMeetingId(userId, meetingId);
//        return MessageResponse.of("User with id=" + userId +
//                " is deleted from the meeting with id+" + meetingId);
    }

    @Override
    public void deleteByDelegateIdAndMeetingId(Long delegateId, Long meetingId) {
        meetingUserRepository.deleteByDelegateIdAndMeetingId(delegateId, meetingId);
    }

    @Override
    public void save(MeetingUserDto meetingUserDto) {
        meetingUserRepository.save(MeetingUserMapper.INSTANCE.toEntity(meetingUserDto));
    }

    @Override
    public Boolean isUserAvailableByUserIdAndDateAndTime(Long userId, LocalDate date,
                                                         LocalTime startTime, LocalTime endTime) {
        List<MeetingUser> meetingUsers = meetingUserRepository
                .findByUserIdAndDateAndTime(userId, date, startTime, endTime);
        return meetingUsers.isEmpty();
    }
}
