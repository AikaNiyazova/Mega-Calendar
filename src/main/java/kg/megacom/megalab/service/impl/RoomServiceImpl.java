package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.mapper.RoomMapper;
import kg.megacom.megalab.repository.RoomRepository;
import kg.megacom.megalab.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDto create(RoomDto roomDto) {
        return null;
    }

    @Override
    public RoomDto findById(Long id) {
        return RoomMapper.INSTANCE
                .toDto(roomRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Room with ID '" + id + "' doesn't exists")));
    }

    @Override
    public RoomDto update(RoomDto roomDto) {
        return null;
    }

    @Override
    public RoomDto save(RoomDto roomDto) {
        return null;
    }
}
