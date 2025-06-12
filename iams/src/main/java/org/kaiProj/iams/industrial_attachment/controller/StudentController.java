package org.kaiProj.iams.industrial_attachment.controller;

import lombok.RequiredArgsConstructor;

import org.kaiProj.iams.industrial_attachment.dto.StudentDTO;
import org.kaiProj.iams.industrial_attachment.model.Student;
import org.kaiProj.iams.industrial_attachment.model.User;
import org.kaiProj.iams.industrial_attachment.service.StudentService;
import org.kaiProj.iams.industrial_attachment.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final UserService userService;

    @PostMapping("/profile")
    public ResponseEntity<Student> createProfile(@RequestBody StudentDTO studentDTO) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Student student = studentService.createStudentProfile(user, studentDTO);
        return ResponseEntity.ok(student);
    }
}