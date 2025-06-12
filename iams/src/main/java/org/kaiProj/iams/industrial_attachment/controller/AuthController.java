package org.kaiProj.iams.industrial_attachment.controller;

import lombok.RequiredArgsConstructor;

import org.kaiProj.iams.industrial_attachment.dto.UserDTO;
import org.kaiProj.iams.industrial_attachment.model.User;
import org.kaiProj.iams.industrial_attachment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDTO userDTO) {
        User user = userService.registerUser(userDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        return userService.findByEmail(userDTO.getEmail())
                .filter(user -> passwordEncoder.matches(userDTO.getPassword(), user.getPassword()))
                .map(user -> ResponseEntity.ok(userService.generateJwtToken(user)))
                .orElseGet(() -> ResponseEntity.status(401).build());
    }
}