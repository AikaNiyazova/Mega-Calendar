package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.request.CreateRoomRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public interface RoomService {

    RoomDto create(CreateRoomRequest request);

    RoomDto findById(Long id);

    RoomDto findByName(String name);

    List<RoomDto> findAll();

    RoomDto update(RoomDto roomDto);

    void delete(Long id);

    RoomDto save(RoomDto roomDto);

    List<RoomDto> findFreeRoomsForDateAndTime(LocalDate date, LocalTime startTime, LocalTime endTime);

}
