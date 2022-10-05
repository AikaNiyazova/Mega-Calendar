package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.MeetingDateTimeDto;
import kg.megacom.megalab.model.dto.MeetingUserDto;
import kg.megacom.megalab.model.mapper.MeetingDateTimeMapper;
import kg.megacom.megalab.model.response.MeetingResponse;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.MeetingDateTimeRepository;
import kg.megacom.megalab.service.MeetingDateTimeService;
import kg.megacom.megalab.service.MeetingUserService;
import kg.megacom.megalab.service.RoomService;
import kg.megacom.megalab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingDateTimeServiceImpl implements MeetingDateTimeService {

    private final MeetingDateTimeRepository meetingDateTimeRepository;
    private final MeetingUserService meetingUserService;
    private final UserService userService;
    private final RoomService roomService;

    @Autowired
    public MeetingDateTimeServiceImpl(MeetingDateTimeRepository meetingDateTimeRepository,
                                      @Lazy MeetingUserService meetingUserService,
                                      UserService userService,
                                      RoomService roomService) {
        this.meetingDateTimeRepository = meetingDateTimeRepository;
        this.meetingUserService = meetingUserService;
        this.userService = userService;
        this.roomService = roomService;
    }

    @Override
    public void save(MeetingDateTimeDto meetingDatesDto) {
        meetingDateTimeRepository.save
                (MeetingDateTimeMapper.INSTANCE.toEntity(meetingDatesDto));
    }

    @Override
    public MeetingDateTimeDto findById(Long id) {
        return MeetingDateTimeMapper.INSTANCE.toDto
                (meetingDateTimeRepository.findByIdAndIsDeletedFalse(id));
    }

    @Override
    public List<MeetingDateTimeDto> findDatesByMeetingId(Long meetingId) {
        return MeetingDateTimeMapper.INSTANCE.toDtoList
                (meetingDateTimeRepository.findDatesByMeetingId(meetingId));
//                meetingDateTimeRepository.findDatesByMeetingId(meetingId)
//                .stream()
//                .map(MeetingDateTime::getMeetingDate)
//                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingDateTimeDto> findAllByUserIdAndDates(Long userId, LocalDate startDate, LocalDate endDate) {
        userService.findById(userId);
        return MeetingDateTimeMapper.INSTANCE.toDtoList
                (meetingDateTimeRepository.findAllByUserIdAndDates(userId, startDate, endDate));
    }

    @Override
    public List<MeetingDateTimeDto> findAllByRoomIdAndDates(Long roomId, LocalDate startDate, LocalDate endDate) {
        roomService.findById(roomId);
        return MeetingDateTimeMapper.INSTANCE.toDtoList
                (meetingDateTimeRepository.findAllByRoomIdAndDates(roomId, startDate, endDate));
    }

//    @Override
//    public MessageResponse delete(MeetingDates meetingDates) {
//        meetingDateTimeRepository.delete(meetingDates);
//        return MessageResponse.of("The meeting will not be repeated");
//    }

    @Override
    @Transactional
    public MessageResponse delete(List<MeetingDateTimeDto> meetingDateTimeDtoList) {
        //todo: Check that only AUTHOR can delete the meeting
        for (MeetingDateTimeDto m : meetingDateTimeDtoList) {
            meetingDateTimeRepository.delete(m.getId());
        }

        MeetingDateTimeDto meetingDateTimeDto = meetingDateTimeDtoList.get(0);
        List<LocalDate> dates = meetingDateTimeDtoList
                .stream().map(MeetingDateTimeDto::getMeetingDate)
                .collect(Collectors.toList());

        MeetingResponse meetingResponse = MeetingResponse
                .builder()
                .meetingDto(meetingDateTimeDto.getMeeting())
                .dates(dates)
                .meetingStartTime(meetingDateTimeDto.getMeetingStartTime())
                .meetingEndTime(meetingDateTimeDto.getMeetingEndTime())
                .roomDto(meetingDateTimeDto.getRoom())
                .build();

        List<MeetingUserDto> meetingUserDtoList = meetingUserService.findAllUsersByMeetingId
                (meetingDateTimeRepository.findMeetingIdById(meetingDateTimeDtoList.get(0).getId()));
        for (MeetingUserDto m : meetingUserDtoList) {
            //todo: send notification to users about cancellation of the meeting (maybe change to User obj)
        }
        return MessageResponse.of("The meeting is deleted");
    }
}
