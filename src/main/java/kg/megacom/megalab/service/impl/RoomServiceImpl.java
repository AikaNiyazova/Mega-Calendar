package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.entity.Room;
import kg.megacom.megalab.model.mapper.RoomMapper;
import kg.megacom.megalab.model.request.CreateRoomRequest;
import kg.megacom.megalab.model.request.UpdateRoomRequest;
import kg.megacom.megalab.repository.RoomRepository;
import kg.megacom.megalab.service.MeetingService;
import kg.megacom.megalab.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final MeetingService meetingService;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, @Lazy MeetingService meetingService) {
        this.roomRepository = roomRepository;
        this.meetingService = meetingService;
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
                .isDeleted(Boolean.FALSE)
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
    public RoomDto update(UpdateRoomRequest request) {

       return roomRepository.findById(request.getRoomId())
                .map(room -> {
                    room.setRoomName(request.getRoomName());
                    room.setRoomCapacity(request.getRoomCapacity());
                    room.setIsDashboardAvailable(request.getIsDashboardAvailable());
                    room.setIsProjectorAvailable(request.getIsProjectorAvailable());
                    room.setIsAcAvailable(request.getIsAcAvailable());
                    roomRepository.save(room);
                    return RoomMapper.INSTANCE.toDto(room);
                }).orElseThrow(() -> new EntityNotFoundException(
                        "Room with id = " + request.getRoomId() + " not found"));
    }

    @Override
    public void delete(Long id) {
        meetingService.setRoomIdNullInMeetings(id);
        roomRepository.deleteById(id);
    }

    @Override
    public RoomDto save(RoomDto room) {
        return RoomMapper.INSTANCE
                .toDto(roomRepository.save(RoomMapper.INSTANCE.toEntity(room)));
    }
}
