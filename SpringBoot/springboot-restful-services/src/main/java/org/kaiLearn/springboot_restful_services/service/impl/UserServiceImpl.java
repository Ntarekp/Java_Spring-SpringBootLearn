package org.kaiLearn.springboot_restful_services.service.impl;

import lombok.AllArgsConstructor;
import org.kaiLearn.springboot_restful_services.dto.UserDTO;
import org.kaiLearn.springboot_restful_services.entity.User;
import org.kaiLearn.springboot_restful_services.mapper.UserMapper;
import org.kaiLearn.springboot_restful_services.repository.UserRepository;
import org.kaiLearn.springboot_restful_services.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; // Added for stream operations

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Convert UserDTO into User JPA Entity
        User user = UserMapper.mapToUser(userDTO);
        User savedUser = userRepository.save(user);
        // Convert User JPA Entity into UserDTO
        UserDTO savedUserDTO = UserMapper.mapToUserDTO(savedUser);
        return savedUserDTO;
    }

    @Override
    public List<UserDTO> createUsers(List<UserDTO> userDTOs) { // Renamed parameter for clarity
        // Convert list of UserDTOs to list of User entities
        List<User> users = userDTOs.stream()
                .map(UserMapper::mapToUser)
                .collect(Collectors.toList());

        // Save all User entities
        List<User> savedUsers = userRepository.saveAll(users);

        // Convert list of saved User entities back to list of UserDTOs
        return savedUsers.stream()
                .map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get(); // Using .get() as in original, consider error handling in production
        return UserMapper.mapToUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        // Convert list of User entities to list of UserDTOs
        return users.stream()
                .map(UserMapper::mapToUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) { // Renamed parameter for clarity
        // Fetch the existing user using the ID from the DTO
        User existingUser = userRepository.findById(userDTO.getId()).get(); // Using .get() as in original, consider error handling

        // Update the fields of the existing entity with data from the DTO
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setEmail(userDTO.getEmail());

        // Save the updated entity
        User updatedUser = userRepository.save(existingUser);

        // Convert the updated User entity to UserDTO before returning
        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}