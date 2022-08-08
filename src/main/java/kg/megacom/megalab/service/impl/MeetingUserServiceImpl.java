package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.MeetingDto;
import kg.megacom.megalab.model.dto.MeetingUserDto;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.mapper.MeetingUserMapper;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.MeetingUserRepository;
import kg.megacom.megalab.service.MeetingUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class MeetingUserServiceImpl implements MeetingUserService {

    private final MeetingUserRepository meetingUserRepository;

    @Autowired
    public MeetingUserServiceImpl(MeetingUserRepository meetingUserRepository) {
        this.meetingUserRepository = meetingUserRepository;
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
}
