package org.kaiLearn.springboot_restful_services.controller;

import lombok.AllArgsConstructor;
import org.kaiLearn.springboot_restful_services.entity.User;
import org.kaiLearn.springboot_restful_services.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    //build create user REST API
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
       User savedUser = userService.createUser(user);
       return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
