package org.kaliLearn.springboot_rest_api.controller;

import org.kaliLearn.springboot_rest_api.bean.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent(){
        Student student = new Student(1, "Kaitare", "Prince");
        return student;
    }

    @GetMapping(" ")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Tuyi","Shime"));
        students.add(new Student(2, "Kai","tare"));
        students.add(new Student(3,"John","Paul"));
        return students;
    }
}
