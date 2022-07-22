package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.model.dto.RoomDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoomService {

    RoomDto save(RoomDto room);

    RoomDto findByName(String name);

    RoomDto findById(Long id);

    List<RoomDto> findAll();

    HiddenRoomDto hideRoom(HiddenRoomDto hiddenRoom);

    void delete(Long id);

}
