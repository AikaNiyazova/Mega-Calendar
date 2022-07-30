package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.HiddenRoomDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HiddenRoomService {

    HiddenRoomDto hideRoom(HiddenRoomDto hiddenRoomDto);
    List<HiddenRoomDto> hideRooms();

}
