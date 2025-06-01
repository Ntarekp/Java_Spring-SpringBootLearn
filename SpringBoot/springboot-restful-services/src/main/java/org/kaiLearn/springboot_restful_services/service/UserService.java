package org.kaiLearn.springboot_restful_services.service;

import org.kaiLearn.springboot_restful_services.dto.UserDTO;
import org.kaiLearn.springboot_restful_services.entity.User;

import java.util.List;

public interface UserService{

   UserDTO createUser(UserDTO user);
   List<User> createUsers(List<User> users);

   User getUserById(Long userId);

   List<User> getAllUsers();

   User updateUser(User user);

   void deleteUser(Long userId);

}
