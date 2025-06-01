package org.kaiLearn.springboot_restful_services.service.impl;

import lombok.AllArgsConstructor;
import org.kaiLearn.springboot_restful_services.dto.UserDTO;
import org.kaiLearn.springboot_restful_services.entity.User;
import org.kaiLearn.springboot_restful_services.repository.UserRepository;
import org.kaiLearn.springboot_restful_services.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
@Override
    public UserDTO createUser(UserDTO userDTO){

    //Convert UserDTO into User JPA Entity
    User user = new User(
            userDTO.getId(),
            userDTO.getFirstName(),
            userDTO.getLastName(),
            userDTO.getEmail()

    );
    User savedUser= userRepository.save(user);

    //Convert User JPA Entity into userDTO
    UserDTO savedUserDTO  = new UserDTO(
            savedUser.getId(),
            savedUser.getFirstName(),
            savedUser.getLastName(),
            savedUser.getEmail()
    );
    return savedUserDTO;
}

    @Override
    public List<User> createUsers(List<User> users) {
        return userRepository.saveAll(users);
    }

    @Override
    public User getUserById(Long userId) {
    Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.get();
    }
    @Override
    public List<User> getAllUsers(){
    return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);
        return updatedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }


}
