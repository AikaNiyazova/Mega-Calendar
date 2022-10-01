package kg.megacom.megalab.controller;

import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.model.request.UpdateHiddenRoomRequest;
import kg.megacom.megalab.model.request.UpdateLabelRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.service.HiddenRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/hidden-room")
public class HiddenRoomController {

    private final HiddenRoomService hiddenRoomService;

    @Autowired
    public HiddenRoomController(HiddenRoomService hiddenRoomService) {
        this.hiddenRoomService = hiddenRoomService;
    }

    @PostMapping("/hide")
    public ResponseEntity<?> hide(@RequestBody HiddenRoomDto hiddenRoom){
        try {
            log.info("Hiding room with id=" + hiddenRoom.getRoom().getId());
            return ResponseEntity.status(HttpStatus.OK).body(hiddenRoomService.hide(hiddenRoom));
        } catch (RuntimeException ex) {
            log.error("Hiding room failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {
            log.info("Finding all hidden rooms");
            return ResponseEntity.ok(hiddenRoomService.findAll());
        } catch (RuntimeException ex) {
            log.error("Finding all hidden rooms failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateHiddenRoomRequest request) {
        try {
            log.info("Updating hidden room with id=" + request.getId());
            return ResponseEntity.ok(hiddenRoomService.update(request));
        } catch (RuntimeException ex) {
            log.error("Updating hidden room failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

}
