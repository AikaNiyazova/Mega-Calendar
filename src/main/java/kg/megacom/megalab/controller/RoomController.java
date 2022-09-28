package kg.megacom.megalab.controller;

import kg.megacom.megalab.exception.room.RoomIsExistsException;
import kg.megacom.megalab.exception.room.RoomNotFoundException;
import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.mapper.RoomMapper;
import kg.megacom.megalab.model.request.CreateRoomRequest;
import kg.megacom.megalab.model.request.UpdateRoomRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateRoomRequest request){
        RoomDto roomDto = roomService.findByName(request.getRoomName());
        if(roomDto!=null){
            log.info("Failed to create room");
            throw new RoomIsExistsException("Room with name = '" + roomDto.getRoomName()+"' is exists");
        }
        log.info("Room created successfully");
        return new ResponseEntity<>(roomService.create(request),HttpStatus.OK);
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){

        if(id==null){
            log.info("Failed to get Room with ID '" + id + "'");
            return new ResponseEntity<RoomDto>(HttpStatus.BAD_REQUEST);
        }
        log.info("Room with ID '" + id + "' found successfully");
        return new ResponseEntity<>(roomService.findById(id),HttpStatus.OK);
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(){
        List<RoomDto> rooms = roomService.findAll();
        if(rooms.isEmpty()){
            log.info("Failed to get all Rooms");
            throw new RoomNotFoundException("There is no Rooms");
        }
        log.info("Rooms found successfully");
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){

        log.info("Room with ID '" + id + "' was successfully deleted");
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateRoomRequest request){
        try {
            log.info("Updating room with id = " + request.getRoomId());
            return ResponseEntity.ok(roomService.update(request));
        } catch (RuntimeException ex) {
            log.error("Updating position failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

}
