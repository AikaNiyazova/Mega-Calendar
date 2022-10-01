package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.model.request.UpdateHiddenRoomRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HiddenRoomService {

    HiddenRoomDto hide(HiddenRoomDto hiddenRoomDto);
    List<HiddenRoomDto> findAll();
    HiddenRoomDto update(UpdateHiddenRoomRequest request);

}
