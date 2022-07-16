package kg.megacom.megalab.controller;

import kg.megacom.megalab.model.dto.PositionDto;
import kg.megacom.megalab.model.request.CreatePositionRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.service.PositionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/position")
public class PositionController {

    private final PositionService positionService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreatePositionRequest request) {
        try {
            log.info("Creating position");
            return ResponseEntity.status(HttpStatus.CREATED).body(positionService.create(request));
        } catch (RuntimeException ex) {
            log.error("Position creation failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            log.info("Finding position with id=" + id);
            return ResponseEntity.ok(positionService.findById(id));
        } catch (RuntimeException ex) {
            log.error("Finding position failed. Position with id=" + id + " not found");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody PositionDto positionDto) {
        try {
            log.info("Updating position with id=" + positionDto.getId());
            return ResponseEntity.ok(positionService.update(positionDto));
        } catch (RuntimeException ex) {
            log.error("Updating position failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            log.info("Deleting position with id=" + id);
            return ResponseEntity.ok(positionService.delete(id));
        } catch (RuntimeException ex) {
            log.error("Deleting position failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

}
