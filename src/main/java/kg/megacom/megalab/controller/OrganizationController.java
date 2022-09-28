package kg.megacom.megalab.controller;

import kg.megacom.megalab.model.request.CreateOrganizationRequest;
import kg.megacom.megalab.model.request.SetAdminRequest;
import kg.megacom.megalab.model.request.UpdateOrganizationRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.service.OrganizationService;
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

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {
            log.info("Finding all organizations");
            return ResponseEntity.ok(organizationService.findAll());
        } catch (RuntimeException ex) {
            log.error("Finding all organizations failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PatchMapping("/set-admin")
    public ResponseEntity<?> setAdmin(@RequestBody SetAdminRequest request) {
        try {
            log.info("Setting admin with user_id=" + request.getAdminId() +
                    " to organization with id=" + request.getOrganizationId());
            return ResponseEntity.ok(organizationService.setAdmin(request));
        } catch (RuntimeException ex) {
            log.error("Setting admin to organization failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateOrganizationRequest request) {
        try {
            log.info("Updating organization with id=" + request.getOrganizationId());
            return ResponseEntity.ok(organizationService.update(request));
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
