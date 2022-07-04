package kg.megacom.megalab.exception.room;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RoomGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<RoomIncorrectData> notFound(
            RoomNotFoundException exception){
        RoomIncorrectData data = new RoomIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<RoomIncorrectData> isExists(
            RoomIsExistsException exception){
        RoomIncorrectData data = new RoomIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}