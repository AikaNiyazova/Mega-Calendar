package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.model.mapper.HiddenRoomMapper;
import kg.megacom.megalab.model.request.UpdateHiddenRoomRequest;
import kg.megacom.megalab.repository.HiddenRoomRepository;
import kg.megacom.megalab.service.HiddenRoomService;
import kg.megacom.megalab.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class HiddenRoomServiceImpl implements HiddenRoomService {

    private final HiddenRoomRepository repository;
    private final RoomService roomService;

    @Autowired
    public HiddenRoomServiceImpl(HiddenRoomRepository repository, RoomService roomService) {
        this.repository = repository;
        this.roomService = roomService;
    }

    @Override
    public HiddenRoomDto hide(HiddenRoomDto hiddenRoomDto) {
        roomService.findById(hiddenRoomDto.getRoom().getId());
        return HiddenRoomMapper.INSTANCE.toDto
                (repository.save
                        (HiddenRoomMapper.INSTANCE.toEntity(hiddenRoomDto)));
    }

    @Override
    public List<HiddenRoomDto> findAll() {
        return HiddenRoomMapper.INSTANCE.toDtoList(repository.findAllCurrent());
    }

    @Override
    public HiddenRoomDto update(UpdateHiddenRoomRequest request) {
        return repository.findById(request.getId())
                .map(hiddenRoom -> {
                    hiddenRoom.setHidingStartDate(request.getHidingStartDate());
                    hiddenRoom.setHidingEndDate(request.getHidingEndDate());
                    hiddenRoom.setReasonForHiding(request.getReasonForHiding());
                    repository.save(hiddenRoom);
                return HiddenRoomMapper.INSTANCE.toDto(hiddenRoom);
                }).orElseThrow(() -> new EntityNotFoundException
                        ("Hidden Room with id=" + request.getId() + " not found"));
    }

}
