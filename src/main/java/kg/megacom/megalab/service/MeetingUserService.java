package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.dto.MeetingUserDto;
import kg.megacom.megalab.model.enums.Status;
import kg.megacom.megalab.model.request.UpdateMeetingUserRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public interface MeetingUserService {

    MeetingUserDto update(UpdateMeetingUserRequest request);

    List<MeetingDto> findByLabel(Long labelId);

    List<MeetingDto> findByDate(LocalDate date);

    List<MeetingDto> findByDates(LocalDate startDate, LocalDate endDate);

    List<MeetingUserDto> findAllUsersByMeetingIdAcceptedAndPending(Long meetingId);

    List<MeetingUserDto> findAllUsersByMeetingId(Long meetingId);

    MeetingUserDto findByUserIdAndMeetingId(Long userId, Long meetingId); //todo: remove?

    void changeStatus(Long meetingId, Status status);

    void deleteByMeetingId(Long meetingId);

    void deleteByUserIdAndMeetingId(Long userId, Long meetingId);

    void deleteByDelegateIdAndMeetingId(Long delegateId, Long meetingId);

    MeetingUserDto save(MeetingUserDto meetingUserDto);

    Boolean isUserAvailableByUserIdAndDateAndTime(Long userId, LocalDate date,
                                                  LocalTime startTime, LocalTime endTime);

}
