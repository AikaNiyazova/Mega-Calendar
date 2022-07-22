package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.entity.HiddenRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;


public interface HiddenRoomService{

    HiddenRoomDto hideRoom(HiddenRoomDto hiddenRoomDto);
    List<HiddenRoomDto> hideRooms();
}
