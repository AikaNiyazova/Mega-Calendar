package kg.megacom.megalab.controller;

import kg.megacom.megalab.exception.user.WrongPasswordException;
import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.request.UpdateProfileRequest;
import kg.megacom.megalab.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest request){

        UserDto user = userService.findById(request.getUserId());

        if(request.getStatus()!=null){
            user.setStatus(request.getStatus());
            log.info("Status for User with ID '" + request.getUserId() + "' updated successfully");
        }
        if(request.getPhotoPath()!=null){
            user.setPhotoPath(request.getPhotoPath());
            log.info("Photo for User with ID '" + request.getUserId() + "' updated successfully");
        }
        if(request.getNewPassword()!=null){
            if(user.getPassword().equals(request.getCurPassword())){
                user.setPassword(request.getNewPassword());
                log.info("Password for User with ID '" + request.getUserId() + "' updated successfully");
            }else{
                throw new WrongPasswordException("Password is wrong");
            }
        }
        return new ResponseEntity<>(userService.save(user),HttpStatus.OK);
    }
}