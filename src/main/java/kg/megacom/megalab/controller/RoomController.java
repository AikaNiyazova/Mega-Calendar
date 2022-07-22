package kg.megacom.megalab.controller;

import kg.megacom.megalab.exception.room.RoomIsExistsException;
import kg.megacom.megalab.exception.room.RoomNotFoundException;
import kg.megacom.megalab.model.dto.HiddenRoomDto;
import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.entity.HiddenRoom;
import kg.megacom.megalab.service.HiddenRoomService;
import kg.megacom.megalab.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private HiddenRoomService hiddenRoomService;


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RoomDto roomDto){
        RoomDto room = roomService.findByName(roomDto.getRoomName());
        if(room != null){
            log.info("Failed to save room");
            throw new RoomIsExistsException("Room with name = '" + roomDto.getRoomName()+"' is exists");
        }
        log.info("Room added successfully");
        return new ResponseEntity<>(roomService.save(roomDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){

        if(id==null){
            log.info("Failed to get Room with ID '" + id + "'");
            return new ResponseEntity<RoomDto>(HttpStatus.BAD_REQUEST);
        }
        log.info("Room with ID '" + id + "' found successfully");
        return new ResponseEntity<>(roomService.findById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<RoomDto> rooms = roomService.findAll();
        if(rooms.isEmpty()){
            log.info("Failed to get all Rooms");
            throw new RoomNotFoundException("There is no Rooms");
        }
        log.info("Rooms found successfully");
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){

        log.info("Room with ID '" + id + "' was successfully deleted");
        roomService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto){
        log.info("Room with ID '" + roomDto.getId() + "' was successfully updated");
        return new ResponseEntity<>(roomService.save(roomDto),HttpStatus.OK);
    }

    @PostMapping("/hide")
    public ResponseEntity<?> hideRoom(@RequestBody HiddenRoomDto hiddenRoom){

        log.info("Room with ID '" + hiddenRoom.getRoom().getId() + "' hided successfully");
        return new ResponseEntity<>(hiddenRoomService.hideRoom(hiddenRoom),HttpStatus.OK);

    }






}
