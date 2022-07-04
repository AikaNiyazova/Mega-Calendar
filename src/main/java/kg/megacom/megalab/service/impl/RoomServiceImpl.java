package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.exception.room.RoomNotFoundException;
import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.mapper.RoomMapper;
import kg.megacom.megalab.repository.RoomRepository;
import kg.megacom.megalab.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public RoomDto save(RoomDto room) {
        return RoomMapper.INSTANCE
                .toDto(roomRepository.save(RoomMapper.INSTANCE.toEntity(room)));

    }

    @Override
    public RoomDto findByName(String name) {
        return RoomMapper.INSTANCE
                .toDto(roomRepository.findByRoomName(name));
    }

    @Override
    public RoomDto findById(Long id) {
        return RoomMapper.INSTANCE
                .toDto(roomRepository.findById(id)
                        .orElseThrow(() -> new RoomNotFoundException("Room with ID '" + id + "' doesn't exists")));
    }

    @Override
    public List<RoomDto> findAll() {
        return RoomMapper.INSTANCE.toDtoList(roomRepository.findAll());
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
