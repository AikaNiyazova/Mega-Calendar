package kg.megacom.megalab.service.impl;

import kg.megacom.megalab.model.dto.UserDto;
import kg.megacom.megalab.model.mapper.UserMapper;
import kg.megacom.megalab.model.request.CreateUserRequest;
import kg.megacom.megalab.model.response.ReadUserProfileResponse;
import kg.megacom.megalab.repository.UserRepository;
import kg.megacom.megalab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(CreateUserRequest request) {
        return null;
    }

    @Override
    public UserDto findById(Long id) {
        return UserMapper.INSTANCE
                .toDto(userRepository.findByIdAndIsDeletedFalse(id)
                        .orElseThrow(() -> new EntityNotFoundException("User with id=" + id + " not found.")));
    }

    @Override
    public ReadUserProfileResponse readProfile(Long id) {
        return userRepository.readProfile(id).orElseThrow(() -> new EntityNotFoundException
                ("User with id=" + id + " not found"));
    }

    @Override
    public UserDto update(UserDto userDto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        userRepository.findByIdAndIsDeletedFalse(id).map(user -> {
            user.setIsDeleted(true);
            return userRepository.save(user);
        }).orElseThrow(() -> new EntityNotFoundException("User with id=" + id + " not found"));
    }

    @Override
    public void deleteUsersAndPositions(Long departmentId) {
        userRepository.deleteUsersAndPositions(departmentId);
    }

    @Override
    public UserDto save(UserDto userDto) {
        return null;
    }
}
