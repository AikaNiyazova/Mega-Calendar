package kg.megacom.megalab.service.impl;

//import kg.megacom.megalab.model.dto.MeetingDatesDto;
import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.entity.*;
import kg.megacom.megalab.model.enums.MemberType;
import kg.megacom.megalab.model.mapper.*;
import kg.megacom.megalab.model.request.CreateMeetingRequest;
import kg.megacom.megalab.model.request.UpdateMeetingRequest;
import kg.megacom.megalab.model.request.UpdateParticipantsRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.MeetingRepository;
import kg.megacom.megalab.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final UserService userService;
    private final RoomService roomService;
//    private final MeetingDatesService meetingDatesService;
    private final MeetingUserService meetingUserService;
    private final LabelService labelService;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository,
                              UserService userService,
                              RoomService roomService,
//                              MeetingDatesService meetingDatesService,
                              MeetingUserService meetingUserService,
                              LabelService labelService) {
        this.meetingRepository = meetingRepository;
        this.userService = userService;
        this.roomService = roomService;
//        this.meetingDatesService = meetingDatesService;
        this.meetingUserService = meetingUserService;
        this.labelService = labelService;
    }

    @Override
    public List<MeetingDto> create(CreateMeetingRequest request) {

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

//        if () {
// todo: check room availability ?
//        }

        User meetingAuthor = UserMapper.INSTANCE.toEntity
                (userService.findById(request.getMeetingAuthorId()));
        Room room = RoomMapper.INSTANCE.toEntity(roomService.findById(request.getRoomId()));

        List<MeetingDto> meetingDtoList = new ArrayList<>();

        for (LocalDate meetingDate : request.getMeetingDates()) {
            Meeting meeting = Meeting
                    .builder()
                    .meetingAuthor(meetingAuthor)
                    .meetingTopic(request.getMeetingTopic())
                    .meetingDate(meetingDate)
                    .meetingStartTime(request.getMeetingStartTime())
                    .meetingEndTime(request.getMeetingEndTime())
                    .room(room)
//                .address(request.getAddress())
                    .isVisible(request.getIsVisible())
//                .isRepeatable(request.getIsRepeatable())
                    .build();
            meetingRepository.save(meeting);
            meetingDtoList.add(MeetingMapper.INSTANCE.toDto(meeting));

            Label label = null;
//                LabelMapper.INSTANCE.toEntity(labelService.findById(request.getLabelId()));

            if (!(request.getLabelId() == null)) {
                label = LabelMapper.INSTANCE.toEntity(labelService.findById(request.getLabelId()));
            }

            MeetingUser author = MeetingUser
                    .builder()
                    .meeting(meeting)
                    .user(meetingAuthor)
                    .memberType(MemberType.AUTHOR)
                    .label(label)
                    .build();
            meetingUserService.save(MeetingUserMapper.INSTANCE.toDto(author));
        }

//        for (LocalDate meetingDate : request.getMeetingDates()) {
//            MeetingDates meetingDates = MeetingDates
//                    .builder()
//                    .meeting(meeting)
//                    .meetingDate(meetingDate)
//                    .build();
//            meetingDatesService.save(MeetingDatesMapper.INSTANCE.toDto(meetingDates));
//        }


//        if (!request.getParticipants().isEmpty()) {
//            for (Long userId : request.getParticipants()) {
//                User participant = UserMapper.INSTANCE.toEntity(userService.findById(userId));
//                MeetingUser meetingUser = MeetingUser
//                        .builder()
//                        .meeting(meeting)
//                        .user(participant)
//                        .memberType(MemberType.PARTICIPANT)
//                        .build();
//                meetingUserService.save(MeetingUserMapper.INSTANCE.toDto(meetingUser));
//            }
//        }
        //todo: send invitation for the meeting to the participants
        return meetingDtoList;
    }

    @Override
    public MessageResponse acceptMeetingByParticipant(Long meetingId, Long participantId) { //todo: set label?

        Meeting meeting = MeetingMapper.INSTANCE.toEntity(findById(meetingId));
        User participant = UserMapper.INSTANCE.toEntity(userService.findById(participantId));

        MeetingUser meetingUser = MeetingUser
                .builder()
                .meeting(meeting)
                .user(participant)
                .memberType(MemberType.PARTICIPANT)
                .build();
        meetingUserService.save(MeetingUserMapper.INSTANCE.toDto(meetingUser));
        //todo: send email confirming user participation to the meeting author
        return MessageResponse.of("The meeting has been successfully added to your calendar");
    }

    @Override
    public MessageResponse acceptMeetingByDelegate(Long meetingId, Long delegateId) {

        Meeting meeting = MeetingMapper.INSTANCE.toEntity(findById(meetingId));
        User delegate = UserMapper.INSTANCE.toEntity(userService.findById(delegateId));

        //todo: maybe delete PARTICIPANT from MeetingUser
        MeetingUser meetingUser = MeetingUser
                .builder()
                .meeting(meeting)
                .user(delegate)
                .memberType(MemberType.DELEGATE)
                .build();
        meetingUserService.save(MeetingUserMapper.INSTANCE.toDto(meetingUser));
        //todo: send email confirming delegate participation to the participant
        //todo: send email confirming user participation to the meeting author
        return MessageResponse.of("The meeting has been successfully added to your calendar");
    }

    @Override
    public MessageResponse declineMeetingByParticipant(Long meetingId, Long participantId) { //todo: reasonForDeclining?
        //todo: send email by participant about declining the meeting to the author
        return MessageResponse.of("The invitation for the meeting has been declined");
    }

    @Override
    @Transactional //todo: check this!!!
    public MessageResponse declineMeetingByDelegate(Long meetingId, Long delegateId) {
        //todo: send email by delegate about declining the meeting to the participant
        //todo: send email by participant about declining the meeting to the author

        meetingUserService.deleteByDelegateIdAndMeetingId(delegateId, meetingId); //todo: decide further
        return MessageResponse.of("The invitation for the meeting has been declined");
    }

    @Override
    public MessageResponse delegateMeeting(Long meetingId, Long participantId, Long delegateId) {

        Meeting meeting = MeetingMapper.INSTANCE.toEntity(findById(meetingId));
        User participant = UserMapper.INSTANCE.toEntity(userService.findById(participantId));
        User delegate = UserMapper.INSTANCE.toEntity(userService.findById(delegateId));

        //todo: maybe NOT save it
        MeetingUser meetingUser = MeetingUser
                .builder()
                .meeting(meeting)
                .user(participant)
                .memberType(MemberType.PARTICIPANT)
                .delegate(delegate)
                .build();
        meetingUserService.save(MeetingUserMapper.INSTANCE.toDto(meetingUser));
        //todo: send email with meeting details from user to delegate
        return MessageResponse.of("Invitation for the meeting has been sent to the delegate");
    }

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

    @Override
    public List<MeetingDto> findAllByUserIdAndDate(Long userId, LocalDate date) {
        userService.findById(userId);
        return MeetingMapper.INSTANCE.toDtoList
                (meetingRepository.findAllByUserIdAndDate(userId, date));
    }

    @Override
    public List<MeetingDto> findAllByUserIdAndTwoDates(Long userId, LocalDate startDate, LocalDate endDate) {
        userService.findById(userId);
        return MeetingMapper.INSTANCE.toDtoList
                (meetingRepository.findAllByUserIdAndTwoDates(userId, startDate, endDate));
    }

    @Override
    public List<MeetingDto> findAllByRoomIdAndDate(Long roomId, LocalDate date) {
        roomService.findById(roomId);
        return MeetingMapper.INSTANCE.toDtoList
                (meetingRepository.findAllByRoomIdAndDate(roomId, date));
    }

    @Override
    public List<MeetingDto> findAllByRoomIdAndTwoDates(Long roomId, LocalDate startDate, LocalDate endDate) {
        roomService.findById(roomId);
        return MeetingMapper.INSTANCE.toDtoList
                (meetingRepository.findAllByRoomIdAndTwoDates(roomId, startDate, endDate));
    }

    @Override
    @Transactional //todo: check this!!!
    public MeetingDto update(UpdateMeetingRequest request) {

        //todo: check whether the author updates the meeting
        //todo: check if the meeting hasn't already passed
        //todo: check if the room is available

        Room room = RoomMapper.INSTANCE.toEntity(roomService.findById(request.getRoomId()));

        MeetingDto meetingDto = meetingRepository.findById(request.getMeetingId())
                .map(meeting -> {
                    meeting.setMeetingDate(request.getMeetingDate());
                    meeting.setMeetingStartTime(request.getMeetingStartTime());
                    meeting.setMeetingEndTime(request.getMeetingEndTime());
                    meeting.setRoom(room);
                    meeting.setIsVisible(request.getIsVisible());
//                    meeting.setIsRepeatable(request.getIsRepeatable());
                meetingRepository.save(meeting);
                return MeetingMapper.INSTANCE.toDto(meeting);
                }).orElseThrow(() -> new EntityNotFoundException
                        ("Meeting with id=" + request.getMeetingId() + " not found"));

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

        return meetingDto;
    }

    @Override
    public MessageResponse updateParticipants(UpdateParticipantsRequest request) {

//        MeetingDto meetingDto = findById(request.getMeetingId());

        List<Long> oldParticipantIds = meetingUserService.findAllUserIdsByMeetingId(request.getMeetingId());
        List<Long> newParticipantIds = request.getParticipants();

        List<Long> additionalParticipants = (List<Long>) CollectionUtils.subtract(newParticipantIds, oldParticipantIds);
        List<Long> excludedParticipants = (List<Long>) CollectionUtils.subtract(oldParticipantIds, newParticipantIds);

        if (!oldParticipantIds.equals(newParticipantIds)) {
            if (!additionalParticipants.isEmpty()/*newParticipantIds.removeAll(oldParticipantIds)*/) {
                for (Long userId : additionalParticipants) {
//                    MeetingUserDto meetingUserDto = MeetingUserDto
//                            .builder()
//                            .meeting(meetingDto)
//                            .user(userService.findById(userId))
//                            .memberType(MemberType.PARTICIPANT)
//                            .build();
//                    meetingUserService.save(meetingUserDto);
                    //todo: send invitation for the meeting to every user
                }
            }
            if (!excludedParticipants.isEmpty()) {
                for (Long userId : excludedParticipants) {
                    meetingUserService.deleteByUserIdAndMeetingId(userId, request.getMeetingId());
                    //todo: send email to every user and inform
                    // that they have been excluded from the meeting
                }
            }
        }
        return MessageResponse.of("List of participants for the meeting has been updated");
    }

    @Override
    @Transactional
    public MessageResponse delete(Long id) {
        //todo: Check that only AUTHOR can delete the meeting
//        meetingDatesService.deleteByMeetingId(id);
        meetingUserService.deleteByMeetingId(id);
        meetingRepository.deleteById(id);
        return MessageResponse.of("Meeting with id=" + id + " is deleted");
        //todo: send email to users about cancellation of the meeting
    }

    @Override
    public void setRoomIdNullInMeetings(Long roomId) {
        meetingRepository.setRoomIdNullInMeetings(roomId);
    }

}
