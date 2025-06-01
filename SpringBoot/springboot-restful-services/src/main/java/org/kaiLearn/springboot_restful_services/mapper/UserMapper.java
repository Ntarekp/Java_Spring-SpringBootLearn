package org.kaiLearn.springboot_restful_services.mapper;

import org.kaiLearn.springboot_restful_services.dto.UserDTO;
import org.kaiLearn.springboot_restful_services.entity.User;

public class UserMapper {
    public static  UserDTO mapToUserDTO(User user){
        //Convert User JPA Entity into UserDTO
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDTO;
    }
    //Convert UserDto into JPA Entity
    public static User mapToUser(UserDTO userDTO){
        User user    = new User(
                userDTO.getId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail()
        );
        return user;
    }
}
