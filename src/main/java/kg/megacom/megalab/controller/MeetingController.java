package kg.megacom.megalab.controller;

import kg.megacom.megalab.model.request.CreateMeetingRequest;
import kg.megacom.megalab.model.request.UpdateMeetingRequest;
import kg.megacom.megalab.model.request.UpdateParticipantsRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.service.MeetingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meeting")
public class MeetingController {

    private final MeetingService meetingService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateMeetingRequest request) {
        try {
            log.info("Creating meeting");
            return ResponseEntity.status(HttpStatus.CREATED).body(meetingService.create(request));
        } catch (RuntimeException ex) {
            log.error("Meeting creation failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PostMapping("/accept-meeting-by-participant/{meetingId}/{participantId}")
    public ResponseEntity<?> acceptMeetingByParticipant(@PathVariable Long meetingId,
                                                        @PathVariable Long participantId) {
        try {
            log.info("Participant with user_id=" + participantId + " is accepting " +
                    "the meeting with id=" + meetingId);
            return ResponseEntity.ok(meetingService.acceptMeetingByParticipant(meetingId, participantId));
        } catch (RuntimeException ex) {
            log.error("Accepting meeting by participant failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PostMapping("/accept-meeting-by-delegate/{meetingId}/{delegateId}")
    public ResponseEntity<?> acceptMeetingByDelegate(@PathVariable Long meetingId,
                                                     @PathVariable Long delegateId) {
        try {
            log.info("Delegate with user_id=" + delegateId + " is accepting " +
                    "the meeting with id=" + meetingId);
            return ResponseEntity.ok(meetingService.acceptMeetingByDelegate(meetingId, delegateId));
        } catch (RuntimeException ex) {
            log.error("Accepting meeting by delegate failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @GetMapping("/decline-meeting-by-participant/{meetingId}/{participantId}")
    public ResponseEntity<?> declineMeetingByParticipant(@PathVariable Long meetingId,
                                                         @PathVariable Long participantId) {
        try {
            log.info("Participant with user_id=" + participantId + " is declining " +
                    "the meeting with id=" + meetingId);
            return ResponseEntity.ok(meetingService.declineMeetingByParticipant(meetingId, participantId));
        } catch (RuntimeException ex) {
            log.error("Declining meeting by participant failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/decline-meeting-by-delegate/{meetingId}/{delegateId}") //todo: decide later
    public ResponseEntity<?> declineMeetingByDelegate(@PathVariable Long meetingId,
                                                      @PathVariable Long delegateId) {
        try {
            log.info("Delegate with user_id=" + delegateId + " is declining " +
                    "the meeting with id=" + meetingId);
            return ResponseEntity.ok(meetingService.declineMeetingByDelegate(meetingId, delegateId));
        } catch (RuntimeException ex) {
            log.error("Declining meeting by delegate failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PostMapping("/delegate-meeting/{meetingId}/{participantId}/{delegateId}")
    public ResponseEntity<?> delegateMeeting(@PathVariable Long meetingId,
                                             @PathVariable Long participantId,
                                             @PathVariable Long delegateId) {
        try {
            log.info("Participant with user_id=" + participantId + " is delegating " +
                    "the meeting with id=" + meetingId + " to delegate with user_id=" + delegateId);
            return ResponseEntity.ok(meetingService.delegateMeeting(meetingId, participantId, delegateId));
        } catch (RuntimeException ex) {
            log.error("Delegating meeting failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            log.info("Finding meeting with id=" + id);
            return ResponseEntity.ok(meetingService.findById(id));
        } catch (RuntimeException ex) {
            log.error("Finding meeting failed. Meeting with id=" + id + " not found");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/find-all-by-user-id-and-date/{userId}")
    public ResponseEntity<?> findAllByUserIdAndDate(@PathVariable Long userId,
                                                    @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        try {
            log.info("Finding meetings for user_id=" + userId + " for date: " + date);
            return ResponseEntity.ok(meetingService.findAllByUserIdAndDate(userId, date));
        } catch (RuntimeException ex) {
            log.error("Finding meetings for user failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @GetMapping("/find-all-by-user-id-and-two-dates/{userId}")
    public ResponseEntity<?> findAllByUserIdAndTwoDates(@PathVariable Long userId,
                                                        @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate startDate,
                                                        @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate endDate) {
        try {
            log.info("Finding meetings for user_id=" + userId + " for the period from: " + startDate
                    + " to: " + endDate);
            return ResponseEntity.ok(meetingService.findAllByUserIdAndTwoDates(userId, startDate, endDate));
        } catch (RuntimeException ex) {
            log.error("Finding meetings for user failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @GetMapping("/find-all-by-room-id-and-date/{roomId}")
    public ResponseEntity<?> findAllByRoomIdAndDate(@PathVariable Long roomId,
                                                    @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        try {
            log.info("Finding meetings for room_id=" + roomId + " for date: " + date);
            return ResponseEntity.ok(meetingService.findAllByRoomIdAndDate(roomId, date));
        } catch (RuntimeException ex) {
            log.error("Finding meetings for room failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @GetMapping("/find-all-by-room-id-and-two-dates/{roomId}")
    public ResponseEntity<?> findAllByRoomIdAndTwoDates(@PathVariable Long roomId,
                                                        @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate startDate,
                                                        @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate endDate) {
        try {
            log.info("Finding meetings for room_id=" + roomId + " for the period from: " + startDate
                    + " to: " + endDate);
            return ResponseEntity.ok(meetingService.findAllByRoomIdAndTwoDates(roomId, startDate, endDate));
        } catch (RuntimeException ex) {
            log.error("Finding meetings for room failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PatchMapping("/update-details")
    public ResponseEntity<?> updateDetails(@RequestBody @Valid UpdateMeetingRequest request) {
        try {
            log.info("Updating meeting with id=" + request.getMeetingId());
            return ResponseEntity.ok(meetingService.update(request));
        } catch (RuntimeException ex) {
            log.error("Updating meeting failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PatchMapping("/update-participants")
    public ResponseEntity<?> updateParticipants(@RequestBody @Valid UpdateParticipantsRequest request) {
        try {
            log.info("Updating participants for the meeting with id=" + request.getMeetingId());
            return ResponseEntity.ok(meetingService.updateParticipants(request));
        } catch (RuntimeException ex) {
            log.error("Updating participants for the meeting failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            log.info("Deleting meeting with id=" + id);
            return ResponseEntity.ok(meetingService.delete(id));
        } catch (RuntimeException ex) {
            log.error("Deleting meeting failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

}
