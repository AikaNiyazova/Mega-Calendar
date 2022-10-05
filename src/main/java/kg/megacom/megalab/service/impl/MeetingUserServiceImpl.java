package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.*;
import kg.megacom.megalab.model.entity.MeetingUser;
import kg.megacom.megalab.model.enums.Status;
import kg.megacom.megalab.model.mapper.MeetingUserMapper;
import kg.megacom.megalab.model.request.UpdateMeetingUserRequest;
import kg.megacom.megalab.model.response.MeetingResponse;
import kg.megacom.megalab.repository.MeetingUserRepository;
import kg.megacom.megalab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingUserServiceImpl implements MeetingUserService {

    private final MeetingUserRepository meetingUserRepository;
    private final LabelService labelService;
    private final UserService userService;
    private final MeetingService meetingService;
    private final MeetingDateTimeService meetingDateTimeService;

    @Autowired
    public MeetingUserServiceImpl(MeetingUserRepository meetingUserRepository,
                                  LabelService labelService,
                                  UserService userService,
                                  MeetingService meetingService,
                                  @Lazy MeetingDateTimeService meetingDateTimeService) {
        this.meetingUserRepository = meetingUserRepository;
        this.labelService = labelService;
        this.userService = userService;
        this.meetingService = meetingService;
        this.meetingDateTimeService = meetingDateTimeService;
    }

    @Override
    public MeetingUserDto update(UpdateMeetingUserRequest request) {

        LabelDto labelDto = null;
        if (!(request.getLabelId() == null)) {
            labelDto = labelService.findById(request.getLabelId());
        }

        UserDto delegate = null;
        if (!(request.getDelegateId() == null)) {
            delegate = userService.findById(request.getDelegateId());
            //todo: send notification to delegate
        }

        MeetingUserDto meetingUserDto = findByUserIdAndMeetingId(request.getSentToUserId(), request.getMeetingId());
//        meetingUserRepository.findById(request.getId())
//                .orElseThrow(() -> new EntityNotFoundException
//                        ("MeetingUser with id=" + request.getId() + " not found"));

        meetingUserDto.setStatus(request.getStatus());
        meetingUserDto.setLabel(labelDto);
        meetingUserDto.setDelegate(delegate);
        meetingUserDto.setReasonForRejection(request.getReasonForRejection());
        save(meetingUserDto);

        MeetingDto meetingDto = meetingService.findById(request.getMeetingId());
        MeetingDateTimeDto meetingDateTimeDto = meetingDateTimeService
                .findDatesByMeetingId(meetingDto.getId()).get(0);
        List<LocalDate> dates = meetingDateTimeService
                .findDatesByMeetingId(request.getMeetingId())
                .stream()
                .map(MeetingDateTimeDto::getMeetingDate)
                .collect(Collectors.toList());

        MeetingResponse meetingResponse = MeetingResponse
                .builder()
                .meetingDto(meetingDto)
                .dates(dates)
                .meetingStartTime(meetingDateTimeDto.getMeetingStartTime())
                .meetingEndTime(meetingDateTimeDto.getMeetingEndTime())
                .roomDto(meetingDateTimeDto.getRoom())
                .build();
        return meetingUserDto;
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
    public List<MeetingUserDto> findAllUsersByMeetingId(Long meetingId) {
        return MeetingUserMapper.INSTANCE.toDtoList
                (meetingUserRepository.findAllUsersByMeetingId(meetingId));
    }

    @Override
    public MeetingUserDto findByUserIdAndMeetingId(Long userId, Long meetingId) {
        return MeetingUserMapper.INSTANCE.toDto
                (meetingUserRepository.findByUserIdAndMeetingId(userId, meetingId));
    }

    @Override
    public void changeStatus(Long meetingId, Status status) {
        List<MeetingUserDto> meetingUserDtoList = findAllUsersByMeetingId(meetingId);
        for (MeetingUserDto meetingUserDto : meetingUserDtoList) {
            meetingUserDto.setStatus(status);
            save(meetingUserDto);
        }
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
