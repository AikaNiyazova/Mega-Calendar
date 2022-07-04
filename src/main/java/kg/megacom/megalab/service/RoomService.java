package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.RoomDto;
import org.springframework.stereotype.Service;

@Service
public interface RoomService {

    RoomDto create(RoomDto roomDto);

    RoomDto findById(Long id);

    RoomDto update(RoomDto roomDto);

    RoomDto save(RoomDto roomDto);

}
