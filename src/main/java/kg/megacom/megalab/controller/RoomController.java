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

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreateRoomRequest request){
        RoomDto roomDto = roomService.findByRoomName(request.getRoomName());
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
            throw new RoomNotFoundException("There are no Rooms");
        }
        log.info("Rooms found successfully");
        return new ResponseEntity<>(rooms,HttpStatus.OK);
    }

    @GetMapping("/find-all-not-hidden-for-date")
    public ResponseEntity<?> findAllNotHiddenForDate(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        try {
            log.info("Finding all rooms without hidden for date " + date);
            return ResponseEntity.ok(roomService.findAllNotHiddenForDate(date));
        } catch (RuntimeException ex) {
            log.error("Finding all rooms without hidden for date " + date + " failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @GetMapping("/find-all-by-room-name")
    public ResponseEntity<?> findAllByRoomName(@RequestParam String name) {
        try {
            log.info("Finding all rooms by name = " + name);
            return ResponseEntity.ok(roomService.findAllByRoomName(name));
        } catch (RuntimeException ex) {
            log.error("Finding all rooms by name = " + name + " failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody RoomDto roomDto){
        log.info("Room with ID '" + roomDto.getId() + "' was successfully updated");
        return new ResponseEntity<>(roomService.save(roomDto),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id ){
        try{
            log.info("Deleting room");
            return ResponseEntity.ok(roomService.delete(id));
        }catch (RuntimeException ex){
            log.error("Deleting room failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/find-free-rooms-for-date-and-time")
    public ResponseEntity<?> findFreeRoomsForDateAndTime(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date,
                                                         @RequestParam /*@DateTimeFormat(pattern = "HH:mm")*/ LocalTime startTime,
                                                         @RequestParam /*@DateTimeFormat(pattern = "HH:mm")*/ LocalTime endTime) {
        try {
            log.info("Finding free rooms for date " + date + " between " + startTime + " and " + endTime);
            return ResponseEntity.ok(roomService.findFreeRoomsForDateAndTime(date, startTime, endTime));
        } catch (RuntimeException ex) {
            log.error("Finding free rooms failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

}
