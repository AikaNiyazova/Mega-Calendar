package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.request.CreateRoomRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public interface RoomService {

    RoomDto create(CreateRoomRequest request);

    RoomDto findById(Long id);

    RoomDto findByRoomName(String name);

    List<RoomDto> findAll();

    List<RoomDto> findAllNotHiddenForDate(LocalDate date);

    List<RoomDto> findAllByRoomName(String roomName);

    RoomDto update(RoomDto roomDto);

    MessageResponse delete(Long id);

    RoomDto save(RoomDto roomDto);

    List<RoomDto> findFreeRoomsForDateAndTime(LocalDate date, LocalTime startTime, LocalTime endTime);

//    void checkRoomAvailabilityForDates(Long roomId, List<LocalDate> dates,
//                                       LocalTime meetingStartTime, LocalTime meetingEndTime);

}
