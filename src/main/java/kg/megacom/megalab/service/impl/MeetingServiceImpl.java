package kg.megacom.megalab.service.impl;

//import kg.megacom.megalab.model.dto.MeetingDatesDto;
import kg.megacom.megalab.model.dto.*;
import kg.megacom.megalab.model.entity.*;
import kg.megacom.megalab.model.enums.Status;
import kg.megacom.megalab.model.mapper.*;
import kg.megacom.megalab.model.request.CreateMeetingRequest;
import kg.megacom.megalab.model.request.UpdateMeetingRequest;
import kg.megacom.megalab.model.request.UpdateParticipantsRequest;
import kg.megacom.megalab.model.response.MeetingResponse;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.MeetingRepository;
import kg.megacom.megalab.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final UserService userService;
    private final RoomService roomService;
    private final MeetingDateTimeService meetingDateTimeService;
    private final MeetingUserService meetingUserService;
    private final LabelService labelService;
    private final NotificationService notificationService;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository,
                              UserService userService,
                              RoomService roomService,
                              /*@Lazy*/ MeetingDateTimeService meetingDateTimeService,
                              @Lazy MeetingUserService meetingUserService,
                              LabelService labelService,
                              NotificationService notificationService) {
        this.meetingRepository = meetingRepository;
        this.userService = userService;
        this.roomService = roomService;
        this.meetingDateTimeService = meetingDateTimeService;
        this.meetingUserService = meetingUserService;
        this.labelService = labelService;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public MeetingResponse create(CreateMeetingRequest request) {

        if (request.getMeetingDates().get(0).isBefore(LocalDate.now())) {
            throw new RuntimeException("Meeting date should be no earlier than today");
        }
        if (request.getMeetingDates().get(0).isEqual(LocalDate.now()) &&
                request.getMeetingStartTime().isBefore(LocalTime.now())) {
            throw new RuntimeException("Meeting start time should be no earlier than now");
        }
        if (request.getMeetingEndTime().isBefore(request.getMeetingStartTime())) {
            throw new RuntimeException("Meeting end time should be no earlier than its start time");
        }

//        roomService.checkRoomAvailabilityForDates(request.getRoomId(), request.getMeetingDates(),
//                request.getMeetingStartTime(), request.getMeetingEndTime());
//
//        RoomDto roomDto = roomService.findById(request.getRoomId());
//        List<LocalDate> busyDates = new ArrayList<>();
//        for (LocalDate meetingDate : request.getMeetingDates()) {
//            List<RoomDto> freeRooms = roomService.findFreeRoomsForDateAndTime(meetingDate,
//                    request.getMeetingStartTime(), request.getMeetingEndTime());
//            if (!freeRooms.contains(roomDto)) {
//                busyDates.add(meetingDate);
//            }
//        }
//        if (!busyDates.isEmpty()) {
//            throw new RuntimeException("Room with id=" + request.getRoomId() +
//                    " is not available at this time for date(s): " + busyDates);
//        }

        User meetingAuthor = UserMapper.INSTANCE.toEntity
                (userService.findById(request.getMeetingAuthorId()));
        RoomDto roomDto = roomService.findById(request.getRoomId());

        Meeting meeting = Meeting
                .builder()
                .meetingAuthor(meetingAuthor)
                .meetingTopic(request.getMeetingTopic())
//                    .meetingDate(meetingDate)
//                    .meetingStartTime(request.getMeetingStartTime())
//                    .meetingEndTime(request.getMeetingEndTime())
//                .room(room)
//                .address(request.getAddress())
                .isVisible(request.getIsVisible())
                .isRepeatable(request.getIsRepeatable())
//                    .isDeleted(false)
                .build();
        meetingRepository.save(meeting);
        MeetingDto meetingDto = MeetingMapper.INSTANCE.toDto(meeting);

        Label label = null;
        if (!(request.getLabelId() == null)) {
            label = LabelMapper.INSTANCE.toEntity(labelService.findById(request.getLabelId()));
        }

        MeetingUser author = MeetingUser
                .builder()
                .meeting(meeting)
                .user(meetingAuthor)
                .status(Status.ACCEPTED)
                .label(label)
                .build();
        meetingUserService.save(MeetingUserMapper.INSTANCE.toDto(author));

        List<MeetingDateTimeDto> meetingDateTimeDtoList = new ArrayList<>();
        List<LocalDate> dates = new ArrayList<>();
        for (LocalDate meetingDate : request.getMeetingDates()) {
            MeetingDateTimeDto meetingDateTimeDto = MeetingDateTimeDto
                    .builder()
                    .meeting(meetingDto)
                    .meetingDate(meetingDate)
                    .meetingStartTime(request.getMeetingStartTime())
                    .meetingEndTime(request.getMeetingEndTime())
                    .room(roomDto)
                    .isDeleted(false)
                    .build();
//            MeetingDateTimeDto meetingDateTimeDto = MeetingDateTimeMapper.INSTANCE.toDto(meetingDateTime);
            MeetingDateTimeDto dto = meetingDateTimeService.save(meetingDateTimeDto);
            dates.add(meetingDate);
            meetingDateTimeDtoList.add(dto);
        }

        MeetingResponse meetingResponse = MeetingResponse
                .builder()
                .meetingDto(meetingDto)
                .dates(dates)
                .meetingStartTime(request.getMeetingStartTime())
                .meetingEndTime(request.getMeetingEndTime())
                .roomDto(roomDto)
                .build();

        if (!request.getParticipants().isEmpty()) {
            for (Long userId : request.getParticipants()) {
                UserDto participant = userService.findById(userId);
                MeetingUserDto meetingUserDto = MeetingUserDto
                        .builder()
                        .meeting(meetingDto)
                        .user(participant)
                        .status(Status.PENDING)
                        .build();
                MeetingUserDto dto=meetingUserService.save(meetingUserDto);
                //todo: send invitation for the meeting to the participants
                MeetingDateTimeDto meetingDateTimeDto = meetingDateTimeDtoList.get(0);
                notificationService.sendToParticipant(meetingDateTimeDto, dto);
            }
        }

        return meetingResponse;

    }

//    @Override
//    public MessageResponse acceptMeetingByParticipant(Long meetingId, Long participantId) { //todo: set label?
//
//        Meeting meeting = MeetingMapper.INSTANCE.toEntity(findById(meetingId));
//        User participant = UserMapper.INSTANCE.toEntity(userService.findById(participantId));
//
//        MeetingUser meetingUser = MeetingUser
//                .builder()
//                .meeting(meeting)
//                .user(participant)
//                .status(Status.ACCEPTED)
//                .build();
//        meetingUserService.save(MeetingUserMapper.INSTANCE.toDto(meetingUser));
//        //todo: send email confirming user participation to the meeting author
//        return MessageResponse.of("The meeting has been successfully added to your calendar");
//    }
//
//    @Override
//    public MessageResponse acceptMeetingByDelegate(Long meetingId, Long delegateId) {
//
//        Meeting meeting = MeetingMapper.INSTANCE.toEntity(findById(meetingId));
//        User delegate = UserMapper.INSTANCE.toEntity(userService.findById(delegateId));
//
//        //todo: maybe delete PARTICIPANT from MeetingUser
//        MeetingUser meetingUser = MeetingUser
//                .builder()
//                .meeting(meeting)
//                .user(delegate)
//                .status(Status.ACCEPTED)
//                .build();
//        meetingUserService.save(MeetingUserMapper.INSTANCE.toDto(meetingUser));
//        //todo: send email confirming delegate participation to the participant
//        //todo: send email confirming user participation to the meeting author
//        return MessageResponse.of("The meeting has been successfully added to your calendar");
//    }
//
//    @Override
//    public MessageResponse declineMeetingByParticipant(Long meetingId, Long participantId) { //todo: reasonForDeclining?
//        //todo: send email by participant about declining the meeting to the author
//        return MessageResponse.of("The invitation for the meeting has been declined");
//    }
//
//    @Override
//    @Transactional //todo: check this!!!
//    public MessageResponse declineMeetingByDelegate(Long meetingId, Long delegateId) {
//        //todo: send email by delegate about declining the meeting to the participant
//        //todo: send email by participant about declining the meeting to the author
//
//        meetingUserService.deleteByDelegateIdAndMeetingId(delegateId, meetingId); //todo: decide further
//        return MessageResponse.of("The invitation for the meeting has been declined");
//    }
//
//    @Override
//    public MessageResponse delegateMeeting(Long meetingId, Long participantId, Long delegateId) {
//
//        Meeting meeting = MeetingMapper.INSTANCE.toEntity(findById(meetingId));
//        User participant = UserMapper.INSTANCE.toEntity(userService.findById(participantId));
//        User delegate = UserMapper.INSTANCE.toEntity(userService.findById(delegateId));
//
//        //todo: maybe NOT save it
//        MeetingUser meetingUser = MeetingUser
//                .builder()
//                .meeting(meeting)
//                .user(participant)
//                .status(Status.DELEGATED)
//                .delegate(delegate)
//                .build();
//        meetingUserService.save(MeetingUserMapper.INSTANCE.toDto(meetingUser));
//        //todo: send email with meeting details from user to delegate
//        return MessageResponse.of("Invitation for the meeting has been sent to the delegate");
//    }

    @Override
    public MeetingDto findById(Long id) {
        return MeetingMapper.INSTANCE.toDto(meetingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException
                        ("Meeting with id=" + id + " not found")));
    }

    @Override
    public List<MeetingDto> findAll() {
        return MeetingMapper.INSTANCE.toDtoList(meetingRepository.findAll());
    }
    //todo: remove?

//    @Override
//    public List<MeetingDto> findAllByUserIdAndDate(Long userId, LocalDate date) {
//        userService.findById(userId);
//        return MeetingMapper.INSTANCE.toDtoList
//                (meetingRepository.findAllByUserIdAndDate(userId, date));
//    }

//    @Override
//    public List<MeetingDto> findAllByRoomIdAndDate(Long roomId, LocalDate date) {
//        roomService.findById(roomId);
//        return MeetingMapper.INSTANCE.toDtoList
//                (meetingRepository.findAllByRoomIdAndDate(roomId, date));
//    }

    @Override
    @Transactional //todo: check this!!!
    public MeetingResponse update(UpdateMeetingRequest request) {

        //todo: check whether the author updates the meeting
        //todo: check if the meeting hasn't already passed
        //todo: check if the room is available

        MeetingDateTimeDto curDateTimeDto = meetingDateTimeService
                .findById(request.getMeetingDateTimeId());

        if (curDateTimeDto.getMeetingDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("You cannot modify the meeting that has already passed");
        }

//        roomService.checkRoomAvailabilityForDates(request.getRoomId(), request.getMeetingDates(),
//                request.getMeetingStartTime(), request.getMeetingEndTime());

        Long meetingId = request.getMeetingId();
        MeetingDto meetingDto = findById(meetingId);
        RoomDto roomDto = roomService.findById(request.getRoomId());

        List<LocalDate> newMeetingDates = request.getMeetingDates();

        List<MeetingDateTimeDto> meetingDateTimeDtoList = meetingDateTimeService
                .findDatesByMeetingId(request.getMeetingId())
                .stream().filter(meetingDateTimeDto ->
                        !meetingDateTimeDto.getMeetingDate()
                        .isBefore(newMeetingDates.get(0)))
                .collect(Collectors.toList());

        List<LocalDate> curMeetingDates = meetingDateTimeDtoList
                .stream().map(MeetingDateTimeDto::getMeetingDate)
                .collect(Collectors.toList());

//        List<Long> meetingDateTimeIds = meetingDateTimeDtoList
//                .stream().map(MeetingDateTimeDto::getId)
//                .collect(Collectors.toList());

        List<MeetingDateTimeDto> newList = new ArrayList<>();
        if (!curMeetingDates.equals(newMeetingDates)) {
            meetingDateTimeService.delete(meetingDateTimeDtoList);
            for (LocalDate date : newMeetingDates) {
                MeetingDateTimeDto meetingDateTimeDto = MeetingDateTimeDto
                        .builder()
                        .meeting(meetingDto)
                        .meetingDate(date)
                        .meetingStartTime(request.getMeetingStartTime())
                        .meetingEndTime(request.getMeetingEndTime())
                        .room(roomDto)
                        .isDeleted(false)
                        .build();
                meetingDateTimeService.save(meetingDateTimeDto);
                newList.add(meetingDateTimeDto);
            }
            meetingUserService.changeStatus(meetingId, Status.PENDING);
            //todo: send notification to users for approval
        } else if (!curDateTimeDto.getMeetingStartTime().equals(request.getMeetingStartTime()) ||
                !curDateTimeDto.getMeetingEndTime().equals(request.getMeetingEndTime())) {
            for (MeetingDateTimeDto mdt : meetingDateTimeDtoList) {
                mdt.setMeetingStartTime(request.getMeetingStartTime());
                mdt.setMeetingEndTime(request.getMeetingEndTime());
                meetingDateTimeService.save(mdt);
            }
            meetingUserService.changeStatus(meetingId, Status.PENDING);
            //todo: send notification to users for approval
        } else if (!curDateTimeDto.getRoom().getId().equals(request.getRoomId())) {
            for (MeetingDateTimeDto mdt : meetingDateTimeDtoList) {
                mdt.setRoom(roomDto);
                meetingDateTimeService.save(mdt);
            }
            meetingUserService.changeStatus(meetingId, Status.MODIFIED); //todo: here
            //todo: send notification to users for information
        }

        MeetingResponse meetingResponse = MeetingResponse
                .builder()
                .meetingDto(meetingDto)
                .dates(newMeetingDates)
                .meetingStartTime(request.getMeetingStartTime())
                .meetingEndTime(request.getMeetingEndTime())
                .roomDto(roomDto)
                .build();

        List<MeetingUserDto> meetingUserDtoList = meetingUserService.findAllUsersByMeetingId(meetingId);

        for (MeetingUserDto meetingUserDto : meetingUserDtoList) {
            //todo: send notification here
            if (!curMeetingDates.equals(newMeetingDates)) {
                notificationService.sendToParticipant(newList.get(0), meetingUserDto);
            } else {
                notificationService.sendToParticipant(meetingDateTimeDtoList.get(0), meetingUserDto);
            }
        }

        meetingDto.setIsVisible(request.getIsVisible());
        meetingDto.setIsRepeatable(request.getIsRepeatable());
        meetingRepository.save(MeetingMapper.INSTANCE.toEntity(meetingDto));

//        MeetingDto meetingDto = meetingRepository.findById(meetingId)
//                .map(meeting -> {
//                    meeting.setMeetingDate(request.getMeetingDate());
//                    meeting.setMeetingStartTime(request.getMeetingStartTime());
//                    meeting.setMeetingEndTime(request.getMeetingEndTime());
//                    meeting.setRoom(room);
//                    meetingDto.setIsVisible(request.getIsVisible());
//                    meetingDto.setIsRepeatable(request.getIsRepeatable());
//                meetingRepository.save(meetingDto);
//                return MeetingMapper.INSTANCE.toDto(meeting);
//                }).orElseThrow(() -> new EntityNotFoundException
//                        ("Meeting with id=" + meetingId + " not found"));

        Long meetingAuthorId = meetingDto.getMeetingAuthor().getId();
        MeetingUserDto meetingAuthor = meetingUserService.findByUserIdAndMeetingId
                (meetingAuthorId, meetingId);

        LabelDto newLabel = null;
        if (!(request.getLabelId() == null)) {
            newLabel = labelService.findById(request.getLabelId());
        }
        meetingAuthor.setLabel(newLabel);
        meetingUserService.save(meetingAuthor);

        return meetingResponse;
    }

//        LabelDto curLabel = meetingAuthor.getLabel();
//
//        if (!curLabel) {
//
//        } else (!curLabel.equals(newLabel)) {
//            meetingAuthor.setLabel(newLabel);
//            meetingUserService.save(meetingAuthor);
//        }


//        for (int i = 0; i < meetingDateTimeIds.size(); i++) {
//
//        }
//        for (LocalDate date : newMeetingDates) {
//            for (Long meetingDateTimeId : meetingDateTimeIds){
//                MeetingDateTimeDto meetingDateTimeDto = meetingDateTimeService.findById(meetingDateTimeId);
//                meetingDateTimeDto.setMeetingDate(date);
//                meetingDateTimeDto.setMeetingStartTime(request.getMeetingStartTime());
//                meetingDateTimeDto.setMeetingEndTime(request.getMeetingEndTime());
//                meetingDateTimeDto.setRoom(roomDto);
//                meetingDateTimeService.save(meetingDateTimeDto);
//            }
//        }

        //todo: if date or startTime or endTime different, change meetingUser to MODIFIED
        // and send notification to users
//        List<LocalDate> currentMeetingDates = meetingDatesService
//                .findDatesByMeetingId(request.getMeetingId());
//        List<LocalDate> requestMeetingDates = request.getMeetingDate();
//
//        if (!currentMeetingDates.equals(requestMeetingDates)) {
//            meetingDatesService.deleteByMeetingId(request.getMeetingId());
//            for (LocalDate meetingDate : requestMeetingDates) {
//                MeetingDatesDto meetingDatesDto = MeetingDatesDto
//                        .builder()
//                        .meeting(meetingDto)
//                        .meetingDate(meetingDate)
//                        .build();
//                meetingDatesService.save(meetingDatesDto);
//            }
//        }

//        if (request.getIsRepeatable()) {
//            if (meetingDatesDto == null) {
//                MeetingDates meetingDates = MeetingDates
//                        .builder()
//                        .meeting(MeetingMapper.INSTANCE.toEntity(meetingDto))
//                        .daysOfWeek(request.getDaysOfWeek())
//                        .numberOfWeeks(request.getNumberOfWeeks())
//                        .build();
//                meetingDatesService.save(MeetingDatesMapper.INSTANCE
//                        .toDto(meetingDates));
//            } else {
//                meetingDatesDto.setDaysOfWeek(request.getDaysOfWeek());
//                meetingDatesDto.setNumberOfWeeks(request.getNumberOfWeeks());
//                meetingDatesService.save(meetingDatesDto); //todo ???
//            }
//        } else {
//            if (!(meetingDatesDto == null)) {
//                meetingDatesService.delete(MeetingDatesMapper.INSTANCE
//                        .toEntity(meetingDatesDto));
//            }
//        }


    @Override
    @Transactional
    public MessageResponse updateParticipants(UpdateParticipantsRequest request) {

        MeetingDateTimeDto meetingDateTimeDto = request.getMeetingDateTimeDto();
        Long meetingId = meetingDateTimeDto.getMeeting().getId();
        MeetingDto meetingDto = findById(meetingId);

        List<MeetingUserDto> meetingUserDtoList = meetingUserService
                .findAllUsersByMeetingId(meetingId); //todo: optimize

        List<Long> oldParticipantIds = meetingUserDtoList
                .stream().map(MeetingUserDto::getId).collect(Collectors.toList());
        List<Long> newParticipantIds = request.getParticipants();

        List<Long> additionalParticipants = (List<Long>) CollectionUtils.subtract(newParticipantIds, oldParticipantIds);
        List<Long> excludedParticipants = (List<Long>) CollectionUtils.subtract(oldParticipantIds, newParticipantIds);

//        List<LocalDate> dates = meetingDateTimeService
//                .findDatesByMeetingId(meetingId)
//                .stream().map(MeetingDateTimeDto::getMeetingDate)
//                .filter(date -> date.isBefore
//                        (meetingDateTimeDto.getMeetingDate()))
//                .collect(Collectors.toList());
//
//        MeetingResponse meetingResponse = MeetingResponse
//                .builder()
//                .meetingDto(meetingDto)
//                .dates(dates)
//                .meetingStartTime(meetingDateTimeDto.getMeetingStartTime())
//                .meetingEndTime(meetingDateTimeDto.getMeetingEndTime())
//                .roomDto(meetingDateTimeDto.getRoom())
//                .build();

        if (!oldParticipantIds.equals(newParticipantIds)) {
            if (!additionalParticipants.isEmpty()/*newParticipantIds.removeAll(oldParticipantIds)*/) {
                for (Long userId : additionalParticipants) {
                    MeetingUserDto invitedBefore = meetingUserService
                            .findByUserIdAndMeetingId(userId, meetingId);
                    if (!(invitedBefore == null)) {
                        invitedBefore.setStatus(Status.PENDING);
                        meetingUserService.save(invitedBefore);
                        //todo: send invitation for the meeting to every user
                        notificationService.sendToParticipant(meetingDateTimeDto, invitedBefore);
                    } else {
                        MeetingUserDto meetingUserDto = MeetingUserDto
                            .builder()
                            .meeting(meetingDto)
                            .user(userService.findById(userId))
                            .status(Status.PENDING)
                            .build();
                        meetingUserService.save(meetingUserDto);
                        //todo: send invitation for the meeting to every user
                        notificationService.sendToParticipant(meetingDateTimeDto, meetingUserDto);
                    }
                }
            }
            if (!excludedParticipants.isEmpty()) {
                for (Long userId : excludedParticipants) {
                    meetingUserService.deleteByUserIdAndMeetingId(userId, meetingId);
                    MeetingUserDto meetingUserDto = meetingUserService
                            .findByUserIdAndMeetingId(userId, meetingId);
                    //todo: send notification to every user and inform
                    // that they have been excluded from the meeting (CANCELLED)
                    notificationService.sendToParticipant(meetingDateTimeDto, meetingUserDto);
                }
            }
        }
        return MessageResponse.of("List of participants for the meeting has been updated");
    }

    @Override
    @Transactional
    public MessageResponse delete(Long id) {                                    //todo: delete !?
        //todo: Check that only AUTHOR can delete the meeting
//        meetingDatesService.deleteByMeetingId(id);
        meetingUserService.deleteByMeetingId(id);
        meetingRepository.deleteById(id);
        return MessageResponse.of("Meeting with id=" + id + " is deleted");
        //todo: send notification to users about cancellation of the meeting
    }

}
