package kg.megacom.megalab.controller;

import kg.megacom.megalab.model.dto.DepartmentDto;
import kg.megacom.megalab.model.request.CreateDepartmentRequest;
import kg.megacom.megalab.model.request.SetHeadRequest;
import kg.megacom.megalab.model.request.UpdateDepartmentRequest;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateDepartmentRequest request) {
        try {
            log.info("Creating department");
            return ResponseEntity.status(HttpStatus.CREATED).body(departmentService.create(request));
        } catch (RuntimeException ex) {
            log.error("Department creation failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            log.info("Finding department with id=" + id);
            return ResponseEntity.ok(departmentService.findById(id));
        } catch (RuntimeException ex) {
            log.error("Finding department failed. Department with id=" + id + " not found");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAll() {
        try {
            log.info("Finding all departments");
            return ResponseEntity.ok(departmentService.findAll());
        } catch (RuntimeException ex) {
            log.error("Finding all departments failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @GetMapping("/find-all-by-organization-id/{organizationId}")
    public ResponseEntity<?> findAllByOrganizationId(@PathVariable Long organizationId) {
        try {
            log.info("Finding all departments by organization_id=" + organizationId);
            return ResponseEntity.ok(departmentService.findAllByOrganizationId(organizationId));
        } catch (RuntimeException ex) {
            log.error("Finding all departments by organization_id failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PatchMapping("/set-head")
    public ResponseEntity<?> setHead(@RequestBody SetHeadRequest request) {
        try {
            log.info("Setting head with user_id=" + request.getHeadId() +
                    " to department with id=" + request.getDepartmentId());
            return ResponseEntity.ok(departmentService.setHead(request));
        } catch (RuntimeException ex) {
            log.error("Setting head to department failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateDepartmentRequest request) {
        try {
            log.info("Updating department with id=" + request.getDepartmentId());
            return ResponseEntity.ok(departmentService.update(request));
        } catch (RuntimeException ex) {
            log.error("Updating department failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete-and-move-members-to-another-dep/{oldDepartmentId}/{newDepartmentId}")
    public ResponseEntity<?> deleteWithoutMembers(@PathVariable Long oldDepartmentId,
                                                  @PathVariable Long newDepartmentId) {
        try {
            log.info("Deleting department with id=" + oldDepartmentId +
                    " and moving its members to department with id=" + newDepartmentId);
            return ResponseEntity.ok(departmentService.deleteAndMoveMembersToAnotherDep(oldDepartmentId, newDepartmentId));
        } catch (RuntimeException ex) {
            log.error("Deleting department and moving its members to another department failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

    @DeleteMapping("/delete-with-members/{id}")
    public ResponseEntity<?> deleteWithMembers(@PathVariable Long id) {
        try {
            log.info("Deleting department with id=" + id + " and its members");
            return ResponseEntity.ok(departmentService.deleteDepAndMembers(id));
        } catch (RuntimeException ex) {
            log.error("Deleting department with its members failed. " + ex.getMessage());
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse(ex.getMessage()));
        }
    }

}
