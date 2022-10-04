package kg.megacom.megalab.service;

import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.User;
import kg.megacom.megalab.model.request.*;
import kg.megacom.megalab.model.response.FindAllUsersForMobileResponse;
import kg.megacom.megalab.model.response.FindAllUsersForWebResponse;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.model.response.UserProfileResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDto create(CreateUserRequest request);

    UserDto registration(RegistrationRequest request);

    UserDto login(LoginRequest request);

    UserDto findById(Long id);

    List<FindAllUsersForWebResponse> findAllForWebResponse();

    List<FindAllUsersForMobileResponse> findAllForMobileResponse();

    List<UserDto> findAllByName(String name);

    List<UserDto> findAllByOrganizationId(Long organizationId);

    List<UserDto> findAllByDepartmentId(Long departmentId);

    List<UserDto> findAllByPositionId(Long positionId);

//    List<UserDto> findAllUsersByMeetingId(Long meetingId);

    List<UserProfileResponse> readProfile(Long id);

    UserDto updatePersonalInfo(UpdateUserPersonalInfoRequest request);

    MessageResponse updateProfessionalInfo(UpdateUserProfessionalInfoRequest request);

    MessageResponse delete(Long id);

    UserDto save(UserDto userDto);

//    void addPhoto(AddPhotoRequest request);

//    void updateStatus(UserDto userDto);

    //setPassword;
    //changePassword;



}
