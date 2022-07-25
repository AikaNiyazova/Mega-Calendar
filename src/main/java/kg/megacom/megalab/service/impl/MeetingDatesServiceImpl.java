package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.MeetingDatesDto;
import kg.megacom.megalab.model.entity.MeetingDates;
import kg.megacom.megalab.model.mapper.MeetingDatesMapper;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.MeetingDatesRepository;
import kg.megacom.megalab.service.MeetingDatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingDatesServiceImpl implements MeetingDatesService {

    private final MeetingDatesRepository meetingDatesRepository;

    @Autowired
    public MeetingDatesServiceImpl(MeetingDatesRepository meetingDatesRepository) {
        this.meetingDatesRepository = meetingDatesRepository;
    }

    @Override
    public void save(MeetingDatesDto meetingDatesDto) {
        meetingDatesRepository.save
                (MeetingDatesMapper.INSTANCE.toEntity(meetingDatesDto));
    }

    @Override
    public List<LocalDate> findDatesByMeetingId(Long meetingId) {
        return meetingDatesRepository.findDatesByMeetingId(meetingId)
                .stream()
                .map(MeetingDates::getMeetingDate)
                .collect(Collectors.toList());
    }

    @Override
    public MessageResponse delete(MeetingDates meetingDates) {
        meetingDatesRepository.delete(meetingDates);
        return MessageResponse.of("The meeting will not be repeated");
    }

    @Override
    public void deleteByMeetingId(Long meetingId) {
        meetingDatesRepository.deleteByMeetingId(meetingId);
    }
}
