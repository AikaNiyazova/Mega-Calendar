package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.exception.room.RoomNotFoundException;
import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.entity.Room;
import kg.megacom.megalab.model.mapper.HiddenRoomMapper;
import kg.megacom.megalab.model.mapper.RoomMapper;
import kg.megacom.megalab.model.request.CreateRoomRequest;
import kg.megacom.megalab.repository.HiddenRoomRepository;
import kg.megacom.megalab.repository.RoomRepository;
import kg.megacom.megalab.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private HiddenRoomRepository hiddenRoomRepository;

    @Override
    public RoomDto save(RoomDto room) {
        return RoomMapper.INSTANCE
                .toDto(roomRepository.save(RoomMapper.INSTANCE.toEntity(room)));
    }

    @Override
    public RoomDto create(CreateRoomRequest request) {
        Room room = Room.
                builder()
                .roomName(request.getRoomName())
                .location(request.getLocation())
                .isDashboardAvailable(request.getIsDashboardAvailable())
                .isAcAvailable(request.getIsAcAvailable())
                .isProjectorAvailable(request.getIsProjectorAvailable())
                .isDeleted(false)
                .build();
        return RoomMapper.INSTANCE.toDto(roomRepository.save(room));
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
        return RoomMapper.INSTANCE
                .toDtoList(roomRepository.findAll());
    }

    @Override
    public HiddenRoomDto hideRoom(HiddenRoomDto hiddenRoom) {
        return HiddenRoomMapper.INSTANCE
                .toDto(hiddenRoomRepository.save(HiddenRoomMapper.INSTANCE.toEntity(hiddenRoom)));
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteRoomById(id );
    }
}
