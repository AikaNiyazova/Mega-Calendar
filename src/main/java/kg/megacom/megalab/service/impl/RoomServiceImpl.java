package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.entity.Room;
import kg.megacom.megalab.model.mapper.RoomMapper;
import kg.megacom.megalab.model.request.CreateRoomRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.repository.RoomRepository;
import kg.megacom.megalab.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .isDeleted(false)
                .build();
        return RoomMapper.INSTANCE.toDto(roomRepository.save(room));
    }

    @Override
    public RoomDto findById(Long id) {
        return RoomMapper.INSTANCE
                .toDto(roomRepository.findByIdAndIsDeletedFalse(id)
                        .orElseThrow(() -> new EntityNotFoundException("Room with ID '" + id + "' doesn't exist")));
    }

    @Override
    public RoomDto findByRoomName(String name) {
        return RoomMapper.INSTANCE
                .toDto(roomRepository.findByRoomName(name));
    }

    @Override
    public List<RoomDto> findAll() {
        return RoomMapper.INSTANCE
                .toDtoList(roomRepository.findAllAndIsDeletedFalse());
    }

    @Override
    public List<RoomDto> findAllNotHiddenForDate(LocalDate date) {
        return RoomMapper.INSTANCE
                .toDtoList(roomRepository.findAllNotHiddenForDate(date));
    }

    @Override
    public List<RoomDto> findAllByRoomName(String roomName) {
        return RoomMapper.INSTANCE
                .toDtoList(roomRepository.findAllByRoomName(roomName));
    }

    @Override
    public RoomDto update(RoomDto roomDto) {
        return roomRepository.findById(roomDto.getId())
                .map(room -> {
                    room.setRoomName(roomDto.getRoomName());
                    room.setRoomCapacity(roomDto.getRoomCapacity());
                    room.setLocation(roomDto.getLocation());
                    room.setIsDashboardAvailable(roomDto.getIsDashboardAvailable());
                    room.setIsProjectorAvailable(roomDto.getIsProjectorAvailable());
                    room.setIsAcAvailable(roomDto.getIsAcAvailable());
                    roomRepository.save(room);
                return RoomMapper.INSTANCE.toDto(room);
                }).orElseThrow(() -> new EntityNotFoundException
                        ("Room with id=" + roomDto.getId() + " not found"));
    }

    @Override
    @Transactional
    public MessageResponse delete(Long id) {
        //check if there are future meetings in the room
        roomRepository.deleteRoomById(id);
        return MessageResponse.of("Room with id=" + id + " is deleted");
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
