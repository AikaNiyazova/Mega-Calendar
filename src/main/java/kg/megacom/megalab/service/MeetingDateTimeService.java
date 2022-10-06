package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.MeetingDateTimeDto;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface MeetingDateTimeService {

    MeetingDateTimeDto save(MeetingDateTimeDto meetingDateTimeDto);

    MeetingDateTimeDto findById(Long id);

    List<MeetingDateTimeDto> findDatesByMeetingId(Long meetingId);

    List<MeetingDateTimeDto> findAllByUserIdAndDates(Long userId, LocalDate startDate, LocalDate endDate);

    List<MeetingDateTimeDto> findAllByRoomIdAndDates(Long roomId, LocalDate startDate, LocalDate endDate);

//    MessageResponse delete(MeetingDates meetingDates);

    MessageResponse delete(List<MeetingDateTimeDto> meetingDateTimeDtoList);

}
