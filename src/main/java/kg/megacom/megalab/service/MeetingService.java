package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.request.CreateMeetingRequest;
import kg.megacom.megalab.model.request.UpdateMeetingRequest;
import kg.megacom.megalab.model.request.UpdateParticipantsRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface MeetingService {

    MeetingDto create(CreateMeetingRequest request);

    MessageResponse acceptMeetingByParticipant(Long meetingId, Long participantId);

    MessageResponse acceptMeetingByDelegate(Long meetingId, Long delegateId);

    MessageResponse declineMeetingByParticipant(Long meetingId, Long participantId);

    MessageResponse declineMeetingByDelegate(Long meetingId, Long delegateId);

    MessageResponse delegateMeeting(Long meetingId, Long userId, Long delegateId);

    MeetingDto findById(Long id);

    List<MeetingDto> findAllByUserIdAndDate(Long userId, LocalDate date);

    List<MeetingDto> findAllByUserIdAndTwoDates(Long userId, LocalDate startDate, LocalDate endDate);

    List<MeetingDto> findAllByRoomIdAndDate(Long roomId, LocalDate date);

    List<MeetingDto> findAllByRoomIdAndTwoDates(Long roomId, LocalDate startDate, LocalDate endDate);

    MeetingDto update(UpdateMeetingRequest request);

    MessageResponse updateParticipants(UpdateParticipantsRequest request);

    MessageResponse delete(Long id); //todo ???

}
