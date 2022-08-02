package kg.megacom.megalab.controller;

import kg.megacom.megalab.model.dto.RoleDto;
import kg.megacom.megalab.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/v1/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RoleDto roleDto) {
        try{
            log.info("Creating role");
            return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(roleDto));
        } catch (RuntimeException ex) {
            log.error("Role creation failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        try {
            log.info("Finding role with id=" + id);
            return ResponseEntity.ok(roleService.findById(id));
        } catch (RuntimeException ex) {
            log.error("Finding role failed. Role with id=" + id + " not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id ){
        try{
            log.info("Delete role");
            return ResponseEntity.ok(roleService.delete(id));
        }catch (RuntimeException ex){
            log.error("Role deleting is failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}
