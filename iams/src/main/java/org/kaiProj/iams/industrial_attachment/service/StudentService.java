package org.kaiProj.iams.industrial_attachment.service;

import lombok.RequiredArgsConstructor;

import org.kaiProj.iams.industrial_attachment.dto.StudentDTO;
import org.kaiProj.iams.industrial_attachment.model.Student;
import org.kaiProj.iams.industrial_attachment.model.User;
import org.kaiProj.iams.industrial_attachment.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student createStudentProfile(User user, StudentDTO studentDTO) {
        Student student = new Student();
        student.setUser(user);
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setCourse(studentDTO.getCourse());
        student.setYearOfStudy(studentDTO.getYearOfStudy());
        student.setSkills(studentDTO.getSkills());
        student.setCvPath(studentDTO.getCvPath());
        return studentRepository.save(student);
    }

    public Optional<Student> findByUserId(Long userId) {
        return studentRepository.findByUserId(userId);
    }
}