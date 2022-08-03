package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.request.*;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.model.response.ReadUserProfileResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto create(CreateUserRequest request);

    UserDto registration(RegistrationRequest request);

    UserDto login(LoginRequest request);

    UserDto findById(Long id);

    List<UserDto> findAll();

    List<UserDto> findAllByName(String name);

    List<UserDto> findAllByOrganizationId(Long organizationId);

    List<UserDto> findAllByDepartmentId(Long departmentId);

    List<UserDto> findAllByPositionId(Long positionId);

    ReadUserProfileResponse readProfile(Long id);

    UserDto updatePersonalInfo(UpdateUserPersonalInfoRequest request);

    MessageResponse updateProfessionalInfo(UpdateUserProfessionalInfoRequest request);

    MessageResponse delete(Long id);

    void deleteUsersAndPositions(Long departmentId);

    UserDto save(UserDto userDto);

//    void addPhoto(AddPhotoRequest request);

//    void updateStatus(UserDto userDto);

    //setPassword;
    //changePassword;



}
