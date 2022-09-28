package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.request.CreateRoomRequest;
import kg.megacom.megalab.model.request.UpdateRoomRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService {

    RoomDto create(CreateRoomRequest request);

    RoomDto findById(Long id);

    RoomDto findByName(String name);

    List<RoomDto> findAll();

    RoomDto update(UpdateRoomRequest request);

    void delete(Long id);

    RoomDto save(RoomDto roomDto);

}
