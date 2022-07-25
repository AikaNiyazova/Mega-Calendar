package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.dto.MeetingUserDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.MeetingUser;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface MeetingUserService {

    List<MeetingDto> findByLabel(Long labelId);

    List<MeetingDto> findByDate(LocalDate date);

    List<MeetingDto> findByDates(LocalDate startDate, LocalDate endDate);

    List<Long> findAllUserIdsByMeetingId(Long meetingId);

    MeetingUserDto findByUserIdAndMeetingId(Long userId, Long meetingId); //todo: remove?

    void deleteByUserIdAndMeetingId(Long userId, Long meetingId);

    void deleteByDelegateIdAndMeetingId(Long delegateId, Long meetingId);

    void save(MeetingUserDto meetingUserDto);

}
