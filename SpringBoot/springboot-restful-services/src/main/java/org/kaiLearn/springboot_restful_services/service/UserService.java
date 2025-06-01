package org.kaiLearn.springboot_restful_services.service;

import org.kaiLearn.springboot_restful_services.dto.UserDTO;

import java.util.List;

public interface UserService{

   UserDTO createUser(UserDTO user);

   List<UserDTO> createUsers(List<UserDTO> users);

   UserDTO getUserById(Long userId);

   List<UserDTO> getAllUsers();

   UserDTO updateUser(UserDTO user);

   void deleteUser(Long userId);

}
