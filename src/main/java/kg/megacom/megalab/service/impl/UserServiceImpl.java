package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.entity.*;
import kg.megacom.megalab.model.mapper.*;
import kg.megacom.megalab.model.request.*;
import kg.megacom.megalab.model.response.FindAllUsersForMobileResponse;
import kg.megacom.megalab.model.response.FindAllUsersForWebResponse;
import kg.megacom.megalab.model.response.MessageResponse;
import kg.megacom.megalab.model.response.UserProfileResponse;
import kg.megacom.megalab.repository.UserRepository;
import kg.megacom.megalab.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final OrganizationService organizationService;
    private final OrganizationUserService organizationUserService;
    private final DepartmentService departmentService;
    private final DepartmentUserService departmentUserService;
    private final PositionService positionService;
    private final PositionUserService positionUserService;

    @Autowired
    @Lazy
    public UserServiceImpl(UserRepository userRepository,
                           RoleService roleService,
                           OrganizationService organizationService,
                           OrganizationUserService organizationUserService,
                           DepartmentService departmentService,
                           DepartmentUserService departmentUserService,
                           PositionService positionService,
                           PositionUserService positionUserService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.organizationService = organizationService;
        this.organizationUserService = organizationUserService;
        this.departmentService = departmentService;
        this.departmentUserService = departmentUserService;
        this.positionService = positionService;
        this.positionUserService = positionUserService;
    }

    @Override
    public UserDto create(CreateUserRequest request) {
        if (userRepository.existsByEmailAndIsDeletedFalse(request.getEmail())) {
            throw new RuntimeException("Email: " + request.getEmail() + " already in use");
        }
        Role role = RoleMapper.INSTANCE.toEntity(roleService.findById(request.getRoleId()));
        Organization organization = OrganizationMapper.INSTANCE.toEntity(organizationService.findById(request.getOrganizationId()));
        Department department = DepartmentMapper.INSTANCE.toEntity(departmentService.findById(request.getDepartmentId()));
        Position position = PositionMapper.INSTANCE.toEntity(positionService.findById(request.getPositionId()));

        User user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .patronymic(request.getPatronymic())
                .msisdn(request.getMsisdn())
                .email(request.getEmail())
                .isDeleted(Boolean.FALSE)
                .role(role)
                .build();
        userRepository.save(user);

        OrganizationUser organizationUser = OrganizationUser
                .builder()
                .organization(organization)
                .user(user)
                .build();
        organizationUserService.save(OrganizationUserMapper.INSTANCE.toDto(organizationUser));

        DepartmentUser departmentUser = DepartmentUser
                .builder()
                .department(department)
                .user(user)
                .build();
        departmentUserService.save(DepartmentUserMapper.INSTANCE.toDto(departmentUser));

        PositionUser positionUser = PositionUser
                .builder()
                .position(position)
                .user(user)
                .build();
        positionUserService.save(PositionUserMapper.INSTANCE.toDto(positionUser));

        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public UserDto registration(RegistrationRequest request) {

        if (!request.getPassword().equals(request.getConfirmedPassword())) {
            throw new RuntimeException("Password does not match. Try again.");
        }

        return userRepository.findByIdAndIsDeletedFalse(request.getUserId())
                .map(user -> {
                    user.setPassword(request.getPassword());
                    user.setStatus(request.getStatus());
                    user.setPhotoPath(request.getPhotoPath());
                    userRepository.save(user);
                return UserMapper.INSTANCE.toDto(user);
                }).orElseThrow(() -> new RuntimeException
                        ("User with id: " + request.getUserId() + " does not exist"));
    }

    @Override
    public UserDto login(LoginRequest request) {

        if (!userRepository.existsByEmailAndIsDeletedFalse(request.getEmail())) {
            throw new RuntimeException("User with email: " + request.getEmail() + " does not exist");
        } else if (!request.getPassword().equals(
                userRepository.findByEmailAndIsDeletedFalse(request.getEmail()).getPassword())) {
            throw new RuntimeException("Password is incorrect! Try again.");
        }
        return UserMapper.INSTANCE.toDto(userRepository.findByEmailAndIsDeletedFalse(request.getEmail()));
    }

    @Override
    public UserDto findById(Long id) {
        return UserMapper.INSTANCE
                .toDto(userRepository.findByIdAndIsDeletedFalse(id)
                        .orElseThrow(() -> new EntityNotFoundException("User with id=" + id + " not found.")));
    }

    @Override
    public List<FindAllUsersForWebResponse> findAllForWebResponse() {
        return userRepository.findAllUsersForWebResponse();
    }

    @Override
    public List<FindAllUsersForMobileResponse> findAllForMobileResponse() {
        return userRepository.findAllUsersForMobileResponse();
    }

    @Override
    public List<UserDto> findAllByName(String name) {
        return UserMapper.INSTANCE.toDtoList(userRepository.findAllByName(name));
    }

    @Override
    public List<UserDto> findAllByOrganizationId(Long organizationId) {
        organizationService.findById(organizationId);
        return UserMapper.INSTANCE.toDtoList(userRepository.findAllByOrganizationId(organizationId));
    }

    @Override
    public List<UserDto> findAllByDepartmentId(Long departmentId) {
        departmentService.findById(departmentId);
        return UserMapper.INSTANCE.toDtoList(userRepository.findAllByDepartmentId(departmentId));
    }

    @Override
    public List<UserDto> findAllByPositionId(Long positionId) {
        positionService.findById(positionId);
        return UserMapper.INSTANCE.toDtoList(userRepository.findAllByPositionId(positionId));
    }

//    @Override
//    public List<UserDto> findAllUsersByMeetingId(Long meetingId) {
//        return UserMapper.INSTANCE.toDtoList
//                (userRepository.findAllUsersByMeetingId(meetingId));
//    }

    @Override
    public List<UserProfileResponse> readProfile(Long id) {
        return userRepository.readProfile(id);
//                .orElseThrow(() -> new EntityNotFoundException
//                ("User with id=" + id + " not found"));
    }

    @Override
    public UserDto updatePersonalInfo(UpdateUserPersonalInfoRequest request) {
          User user = userRepository.findByIdAndIsDeletedFalse(request.getUserId())
                .map(user1 -> {
                    user1.setFirstName(request.getFirstName());
                    user1.setLastName(request.getLastName());
                    user1.setMsisdn(request.getMsisdn());
                    user1.setEmail(request.getEmail());
                    return userRepository.save(user1);
                }).orElseThrow(() -> new EntityNotFoundException
                          ("User with id= " + request.getUserId() + " not found"));

        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public MessageResponse updateProfessionalInfo(UpdateUserProfessionalInfoRequest request) {
        if(!request.getOldOrganizationId().equals(request.getNewOrganizationId())){

            organizationUserService.changeOrganization
                    (request.getUserId(), request.getOldOrganizationId(), request.getNewOrganizationId());

            departmentUserService.changeDepartmentByUserId
                    (request.getUserId(), request.getOldDepartmentId(), request.getNewDepartmentId());

           positionUserService.changePosition
                    (request.getUserId(), request.getOldPositionId(), request.getNewPositionId());

        } else if (!request.getOldDepartmentId().equals(request.getNewDepartmentId())) {

            departmentUserService.changeDepartmentByUserId
                    (request.getUserId(), request.getOldDepartmentId(), request.getNewDepartmentId());

            positionUserService.changePosition
                    (request.getUserId(), request.getOldPositionId(), request.getNewPositionId());
        } else {
            positionUserService.changePosition
                    (request.getUserId(), request.getOldPositionId(), request.getNewPositionId());
        }
        return MessageResponse.of("Professional info of user with id = " + request.getUserId() + " is updated");
    }

    @Override
    public MessageResponse delete(Long id) {
         userRepository.findByIdAndIsDeletedFalse(id).map(user -> {
            user.setIsDeleted(true);
            return userRepository.save(user);
        }).orElseThrow(() -> new EntityNotFoundException("User with id = " + id + " not found"));
        return MessageResponse.of("User with id = " + id + " is deleted");
    }

    @Override
    public UserDto save(UserDto userDto) {
        return null;
    }
}
