package kg.megacom.megalab.controller;

import kg.megacom.megalab.model.dto.LabelDto;
import kg.megacom.megalab.model.request.CreateLabelRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.service.LabelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/label")
public class LabelController {

    private final LabelService labelService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateLabelRequest request) {
        try {
            log.info("Creating label");
            return ResponseEntity.status(HttpStatus.CREATED).body(labelService.create(request));
        } catch (RuntimeException ex) {
            log.error("Label creation failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            log.info("Finding label with id=" + id);
            return ResponseEntity.ok(labelService.findById(id));
        } catch (RuntimeException ex) {
            log.error("Finding label failed. Label with id=" + id + " not found");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {
            log.info("Finding all labels");
            return ResponseEntity.ok(labelService.findAll());
        } catch (RuntimeException ex) {
            log.error("Finding all labels failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody LabelDto labelDto) {
        try {
            log.info("Updating label with id=" + labelDto.getId());
            return ResponseEntity.ok(labelService.update(labelDto));
        } catch (RuntimeException ex) {
            log.error("Updating label failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            log.info("Deleting label with id=" + id);
            return ResponseEntity.ok(labelService.delete(id));
        } catch (RuntimeException ex) {
            log.error("Deleting label failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

}
