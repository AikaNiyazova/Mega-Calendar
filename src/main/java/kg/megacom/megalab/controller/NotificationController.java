package kg.megacom.megalab.controller;

import kg.megacom.megalab.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/findByUserToId/{id}")
    public ResponseEntity<?> findByUserToId(@PathVariable Long id) {
        try {
            log.info("Finding notifications by participant with id=" + id);
            return ResponseEntity.ok(notificationService.findByUserSendToId(id));
        } catch (RuntimeException ex) {
            log.error("Finding user failed. Participant with id=" + id + " not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/findByUserFromId/{id}")
    public ResponseEntity<?> findByUserFromId(@PathVariable Long id) {
        try {
            log.info("Finding notifications by author with id=" + id);
            return ResponseEntity.ok(notificationService.findByUserSendFromId(id));
        } catch (RuntimeException ex) {
            log.error("Finding user failed. Author with id=" + id + " not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}
