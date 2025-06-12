package org.kaiProj.iams.industrial_attachment.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.kaiProj.iams.industrial_attachment.config.JwtTokenProvider;
import org.kaiProj.iams.industrial_attachment.entity.User;
import org.kaiProj.iams.industrial_attachment.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        if(userRepository.findByUsername(request.getUsername()).isPresent()){
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole().toUpperCase())
                .fullName(request.getFullName())
                .build();

        // If role is STUDENT, set additional fields
        if(user.getRole().equals("STUDENT")){
            user.setCourse(request.getCourse());
            user.setYear(request.getYear());
            user.setSkills(request.getSkills());
            user.setCvPath(request.getCvPath());
        }

        // For COMPANY: set company details and mark as not approved by default.
        if(user.getRole().equals("COMPANY")){
            user.setCompanyName(request.getCompanyName());
            user.setCompanyDescription(request.getCompanyDescription());
            user.setApproved(false);
        }

        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        var userOptional = userRepository.findByUsername(request.getUsername());
        if(userOptional.isEmpty()){
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        User user = userOptional.get();
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        String token = jwtTokenProvider.generateToken(user.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Data
    public static class RegisterRequest {
        private String username;
        private String email;
        private String password;
        private String role; // STUDENT, COMPANY, COORDINATOR, ADMIN
        private String fullName;
        // STUDENT fields
        private String course;
        private String year;
        private String skills;
        private String cvPath;
        // COMPANY fields
        private String companyName;
        private String companyDescription;
    }

    @Data
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @Data
    public static class JwtResponse {
        private final String token;
    }
}
