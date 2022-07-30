package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.model.mapper.HiddenRoomMapper;
import kg.megacom.megalab.repository.HiddenRoomRepository;
import kg.megacom.megalab.service.HiddenRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiddenRoomImpl implements HiddenRoomService {

    private final HiddenRoomRepository repository;

    @Autowired
    public HiddenRoomImpl(HiddenRoomRepository repository) {
        this.repository = repository;
    }

    @Override
    public HiddenRoomDto hideRoom(HiddenRoomDto hiddenRoomDto) {
        return HiddenRoomMapper.INSTANCE.toDto
                (repository.save
                        (HiddenRoomMapper.INSTANCE.toEntity(hiddenRoomDto)));
    }

    @Override
    public List<HiddenRoomDto> hideRooms() {
        return HiddenRoomMapper.INSTANCE.toDtoList(repository.findAll());
    }

}
