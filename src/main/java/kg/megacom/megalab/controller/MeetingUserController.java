package kg.megacom.megalab.controller;

import kg.megacom.megalab.model.request.UpdateMeetingRequest;
import kg.megacom.megalab.model.request.UpdateMeetingUserRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.service.MeetingUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting-user")
public class MeetingUserController {

    private final MeetingUserService meetingUserService;

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody @Valid UpdateMeetingUserRequest request) {
        try {
            log.info("Updating meeting for user with id=" + request.getSentToUserId());
            return ResponseEntity.ok(meetingUserService.update(request));
        } catch (RuntimeException ex) {
            log.error("Updating meeting for user with id=" + request.getSentToUserId() + " failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @GetMapping("/is-user-available-by-user-id-and-date-and-time/{userId}")
    public ResponseEntity<?> isUserAvailableByUserIdAndDateAndTime(@PathVariable Long userId,
                                                                   @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date,
                                                                   @RequestParam LocalTime startTime,
                                                                   @RequestParam LocalTime endTime) {
        try {
            log.info("Finding user's availability for date " + date + " between " + startTime + " and " + endTime);
            return ResponseEntity.ok(meetingUserService.isUserAvailableByUserIdAndDateAndTime(userId, date, startTime, endTime));
        } catch (RuntimeException ex) {
            log.error("Finding user's availability failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @GetMapping("/find-all-users-by-meeting-id/{meetingId}")
    public ResponseEntity<?> findAllUsersByMeetingId(@PathVariable Long meetingId) {
        try {
            log.info("Finding all users for the meeting with id=" + meetingId);
            return ResponseEntity.ok(meetingUserService.findAllUsersByMeetingId(meetingId));
        } catch (RuntimeException ex) {
            log.error("Finding all users for the meeting with id=" + meetingId + " failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

}
