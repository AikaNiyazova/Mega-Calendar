package kg.megacom.megalab.controller;

import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.service.HiddenRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/hidden-room")
public class HiddenRoomController {

    private final HiddenRoomService hiddenRoomService;

    @Autowired
    public HiddenRoomController(HiddenRoomService hiddenRoomService) {
        this.hiddenRoomService = hiddenRoomService;
    }

    @PostMapping("/hide")
    public ResponseEntity<?> hideRoom(@RequestBody HiddenRoomDto hiddenRoom){

        log.info("Room with ID '" + hiddenRoom.getRoom().getId() + "' hided successfully");
        return new ResponseEntity<>(hiddenRoomService.hideRoom(hiddenRoom), HttpStatus.OK);

    }

}
