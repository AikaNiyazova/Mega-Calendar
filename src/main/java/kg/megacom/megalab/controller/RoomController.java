package kg.megacom.megalab.controller;

import kg.megacom.megalab.exception.room.RoomIsExistsException;
import kg.megacom.megalab.exception.room.RoomNotFoundException;
import kg.megacom.megalab.model.dto.RoomDto;
import kg.megacom.megalab.model.request.CreateRoomRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody RoomDto roomDto){
        RoomDto room = roomService.findByName(roomDto.getRoomName());
        if(room != null){
            log.info("Failed to save room");
            throw new RoomIsExistsException("Room with name = '" + roomDto.getRoomName()+"' is exists");
        }
        log.info("Room saved successfully");
        return new ResponseEntity<>(roomService.save(roomDto), HttpStatus.CREATED);
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
    public ResponseEntity<?> updateRoom(@RequestBody RoomDto roomDto){
        log.info("Room with ID '" + roomDto.getId() + "' was successfully updated");
        return new ResponseEntity<>(roomService.save(roomDto),HttpStatus.OK);
    }

    @GetMapping("/find-free-rooms-for-date-and-time")
    public ResponseEntity<?> findFreeRoomsForDateAndTime(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date,
                                                         @RequestParam /*@DateTimeFormat(pattern = "HH:mm")*/ LocalTime startTime,
                                                         @RequestParam /*@DateTimeFormat(pattern = "HH:mm")*/ LocalTime endTime) {
        try {
            log.info("Finding free rooms for date " + date + " between " + startTime + " and: " + endTime);
            return ResponseEntity.ok(roomService.findFreeRoomsForDateAndTime(date, startTime, endTime));
        } catch (RuntimeException ex) {
            log.error("Finding free rooms failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

}
