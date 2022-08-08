package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.entity.Room;
import kg.megacom.megalab.model.mapper.RoomMapper;
import kg.megacom.megalab.model.request.CreateRoomRequest;
import kg.megacom.megalab.repository.RoomRepository;
import kg.megacom.megalab.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomDto create(CreateRoomRequest request) {
        Room room = Room.
                builder()
                .roomName(request.getRoomName())
                .location(request.getLocation())
                .roomCapacity(request.getRoomCapacity())
                .isDashboardAvailable(request.getIsDashboardAvailable())
                .isAcAvailable(request.getIsAcAvailable())
                .isProjectorAvailable(request.getIsProjectorAvailable())
                .build();
        return RoomMapper.INSTANCE.toDto(roomRepository.save(room));
    }

    @Override
    public RoomDto findById(Long id) {
        return RoomMapper.INSTANCE
                .toDto(roomRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Room with ID '" + id + "' doesn't exists")));
    }

    @Override
    public RoomDto findByName(String name) {
        return RoomMapper.INSTANCE
                .toDto(roomRepository.findByRoomName(name));
    }

    @Override
    public List<RoomDto> findAll() {
        return RoomMapper.INSTANCE
                .toDtoList(roomRepository.findAll());
    }

    @Override
    public RoomDto update(RoomDto roomDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);
        //todo: в митингах, которые уже прошли в этом руме, отображается пометка "Удаленное помещение"
    }

    @Override
    public RoomDto save(RoomDto room) {
        return RoomMapper.INSTANCE
                .toDto(roomRepository.save(RoomMapper.INSTANCE.toEntity(room)));
    }

    @Override
    public List<RoomDto> findFreeRoomsForDateAndTime(LocalDate date, LocalTime startTime, LocalTime endTime) {
        return RoomMapper.INSTANCE.toDtoList
                (roomRepository.findFreeRoomsForDateAndTime(date, startTime, endTime));
    }

}
