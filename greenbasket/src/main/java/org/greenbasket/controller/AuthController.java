package org.greenbasket.controller; // Ensure this matches your directory structure

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.greenbasket.dto.LoginDto;
import org.greenbasket.dto.UserDto;
import org.greenbasket.entity.User;
import org.greenbasket.security.JwtTokenProvider;
import org.greenbasket.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        User user = userService.authenticate(loginDto.getUsername(), loginDto.getPassword());
        if (user == null) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        String token = tokenProvider.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        User savedUser = userService.register(userDto);
        return ResponseEntity.ok(savedUser);
    }

    // Inner response class should be entirely inside the AuthController class.
    public static class AuthResponse {
        private String token;
        private User user;

        public AuthResponse(String token, User user) {
            this.token = token;
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public User getUser() {
            return user;
        }
    }
}
