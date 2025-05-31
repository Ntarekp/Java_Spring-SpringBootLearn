package org.kaiLearn.springboot_restful_services.service;

import org.kaiLearn.springboot_restful_services.entity.User;

import java.util.List;

public interface UserService{
   User createUser(User user);

   User getUserById(Long userId);

   List<User> getAllUsers();

   User updateUser(User user);

}
