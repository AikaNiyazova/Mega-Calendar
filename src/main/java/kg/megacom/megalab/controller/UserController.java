package kg.megacom.megalab.controller;

import kg.megacom.megalab.model.request.*;
import kg.megacom.megalab.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateUserRequest request) {
        try {
            log.info("Creating user");
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(request));
        } catch (RuntimeException ex) {
            log.error("User creation failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> find(@PathVariable Long id) {
        try {
            log.info("Finding user with id=" + id);
            return ResponseEntity.ok(userService.findById(id));
        } catch (RuntimeException ex) {
            log.error("Finding user failed. User with id=" + id + " not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/findAll/{organizationId}")
    public ResponseEntity<?> findAllByOrganizationId(@PathVariable Long organizationId) {
        try {
            log.info("Finding all users with organization id=" + organizationId);
            return ResponseEntity.ok(userService.findAllByOrganizationId(organizationId));
        } catch (RuntimeException ex) {
            log.error("Finding all users failed. Organization with id=" + organizationId + " not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/findAllBy/{departmentId}")
    public ResponseEntity<?> findAllByDepartmentId(@PathVariable Long departmentId) {
        try {
            log.info("Finding all users with department id=" + departmentId);
            return ResponseEntity.ok(userService.findAllByDepartmentId(departmentId));
        } catch (RuntimeException ex) {
            log.error("Finding all users failed. Department with id=" + departmentId + " not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/findUsers/{positionId}")
    public ResponseEntity<?> findAllByPositionId(@PathVariable Long positionId) {
        try {
            log.info("Finding all users with position id=" + positionId);
            return ResponseEntity.ok(userService.findAllByPositionId(positionId));
        } catch (RuntimeException ex) {
            log.error("Finding all users failed. Position with id=" + positionId + " not found.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PutMapping("/updatePersonalInfo")
    public ResponseEntity<?> updatePersonalInfo(@RequestBody UpdateUserPersonalInfoRequest request) {
        try {
            log.info("Updating user's personal info");
            return ResponseEntity.status(HttpStatus.OK).body(userService.updatePersonalInfo(request));
        } catch (RuntimeException ex) {
            log.error("User's personal info update is failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping("/updateProfessionalInfo")
    public ResponseEntity updateProfessionalInfo(@RequestBody UpdateUserProfessionalInfoRequest request) {
        try {
            log.info("Updating user's professional info");
            return ResponseEntity.ok(userService.updateProfessionalInfo(request));
        } catch (RuntimeException ex) {
            log.error("User's professional info update is failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id ){
        try{
            log.info("Delete user");
            return ResponseEntity.ok(userService.delete(id));
        }catch (RuntimeException ex){
            log.error("User deleting is failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @GetMapping("/readProfile/{id}")
    public ResponseEntity<?> readProfile(@PathVariable Long id) {
        try {
            log.info("Reading user profile with id=" + id);
            return ResponseEntity.ok(userService.readProfile(id));
        } catch (RuntimeException ex) {
            log.error("Reading user profile failed.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationRequest request) {
        try {
            log.info("Registration of user");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.registration(request));
        } catch (RuntimeException ex) {
            log.error("Registration of user is failed");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginRequest request) {
        try {
            log.info("User login in process");
            return ResponseEntity.ok(userService.login(request));
        } catch (RuntimeException ex) {
            log.error("User login is failed.");
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }
}