package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.request.AddPhotoRequest;
import kg.megacom.megalab.model.request.CreateUserRequest;
import kg.megacom.megalab.model.response.ReadUserProfileResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto create(CreateUserRequest request);

    UserDto findById(Long id);

    ReadUserProfileResponse readProfile(Long id);

    //addPhoto;
//    void addPhoto(AddPhotoRequest request); //todo: ???

    //setPassword;
    //changePassword;

    UserDto update(UserDto userDto);

    void delete(Long id);

    void deleteUsersAndPositions(Long departmentId);

    UserDto save(UserDto userDto);

}
