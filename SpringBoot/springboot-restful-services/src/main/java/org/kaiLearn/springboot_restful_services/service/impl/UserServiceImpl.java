package org.kaiLearn.springboot_restful_services.service.impl;

import lombok.AllArgsConstructor;
import org.kaiLearn.springboot_restful_services.dto.UserDTO;
import org.kaiLearn.springboot_restful_services.entity.User;
import org.kaiLearn.springboot_restful_services.exception.ResourceNotFoundException;
import org.kaiLearn.springboot_restful_services.mapper.UserMapper;
import org.kaiLearn.springboot_restful_services.repository.UserRepository;
import org.kaiLearn.springboot_restful_services.service.UserService;
import org.modelmapper.ModelMapper;
// The @Autowired import is typically not needed when using @AllArgsConstructor for constructor injection
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper; // ModelMapper is now injected via Lombok's @AllArgsConstructor

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        // Convert UserDTO into User JPA Entity
        // User user = UserMapper.mapToUser(userDTO); // <-- Old way using custom mapper
        User user = modelMapper.map(userDTO, User.class); // <-- New way using ModelMapper

        User savedUser = userRepository.save(user);

        // Convert User JPA Entity into UserDTO
        // UserDTO savedUserDTO = UserMapper.mapToUserDTO(savedUser); // <-- Old way using custom mapper
        UserDTO savedUserDTO = modelMapper.map(savedUser, UserDTO.class); // <-- New way using ModelMapper

        return savedUserDTO;
    }

    @Override
    public List<UserDTO> createUsers(List<UserDTO> userDTOs) {
        // Convert list of UserDTOs to list of User entities
        List<User> usersToSave = userDTOs.stream()
                // .map(UserMapper::mapToUser) // <-- Old way using custom mapper
                .map(userDTO -> modelMapper.map(userDTO, User.class)) // <-- New way using ModelMapper
                .collect(Collectors.toList());

        // Save all User entities
        List<User> savedUsers = userRepository.saveAll(usersToSave);

        // Convert list of saved User entities back to list of UserDTOs
        return savedUsers.stream()
                // .map(UserMapper::mapToUserDTO) // <-- Old way using custom mapper
                .map(user -> modelMapper.map(user, UserDTO.class)) // <-- New way using ModelMapper
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        // In a real application, you'd likely throw an exception if the user isn't found,
        // instead of just calling .get() on an empty Optional.
        // User user = optionalUser.get(); // no longer required

        // return UserMapper.mapToUserDTO(user); // <-- Old way using custom mapper
        return modelMapper.map(user, UserDTO.class); // <-- New way using ModelMapper
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        // Convert list of User entities to list of UserDTOs
        // return users.stream().map(UserMapper::mapToUserDTO) // <-- Old way using custom mapper
        //         .collect(Collectors.toList());
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class)) // <-- New way using ModelMapper
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        // Fetch the existing user using the ID from the DTO
        // As above, consider robust error handling (e.g., throwing a custom NotFoundException)
        User existingUser = userRepository.findById(userDTO.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDTO.getId())
        );

        // Update the fields of the existing entity with data from the DTO using ModelMapper.
        // This replaces the need for manual setters like existingUser.setFirstName(userDTO.getFirstName());
        // modelMapper.map(userDTO, existingUser) effectively updates matching properties from userDTO onto existingUser.
        // existingUser.setFirstName(userDTO.getFirstName()); // <-- Old way (manual update)
        // existingUser.setLastName(userDTO.getLastName());   // <-- Old way (manual update)
        // existingUser.setEmail(userDTO.getEmail());         // <-- Old way (manual update)
        modelMapper.map(userDTO, existingUser); // <-- New way using ModelMapper to update existing entity

        // Save the updated entity
        User updatedUser = userRepository.save(existingUser);

        // Convert the updated User entity to UserDTO before returning
        // return UserMapper.mapToUserDTO(updatedUser); // <-- Old way using custom mapper
        return modelMapper.map(updatedUser, UserDTO.class); // <-- New way using ModelMapper
    }

    @Override
    public void deleteUser(Long userId) {
        // First check if the user exists before attempting to delete
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "id", userId);
        }
        userRepository.deleteById(userId);
    }
}
