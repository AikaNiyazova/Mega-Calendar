package kg.megacom.megalab.controller;

import kg.megacom.megalab.model.dto.OrganizationDto;
import kg.megacom.megalab.model.request.CreateOrganizationRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateOrganizationRequest request) {
        try {
            log.info("Creating organization");
            return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.create(request));
        } catch (RuntimeException ex) {
            log.error("Organization creation failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            log.info("Finding organization with id=" + id);
            return ResponseEntity.ok(organizationService.findById(id));
        } catch (RuntimeException ex) {
            log.error("Finding organization failed. Organization with id=" + id + " not found");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PatchMapping("/set-admin")
    public ResponseEntity<?> setAdmin(@RequestBody OrganizationDto organizationDto) {
        try {
            log.info("Setting admin with id=" + organizationDto.getAdmin().getId() +
                    " to organization with id=" + organizationDto.getId());
            return ResponseEntity.ok(organizationService.setAdmin(organizationDto));
        } catch (RuntimeException ex) {
            log.error("Setting admin to organization failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody OrganizationDto organizationDto) {
        try {
            log.info("Updating organization with id=" + organizationDto.getId());
            return ResponseEntity.ok(organizationService.update(organizationDto));
        } catch (RuntimeException ex) {
            log.error("Updating organization failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            log.info("Deleting organization with id=" + id);
            return ResponseEntity.ok(organizationService.delete(id));
        } catch (RuntimeException ex) {
            log.error("Deleting organization failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

}
