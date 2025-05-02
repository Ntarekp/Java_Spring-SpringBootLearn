package org.kaliLearn.springboot_rest_api.controller;

import org.apache.coyote.Response;
import org.kaliLearn.springboot_rest_api.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    //http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "Kaitare",
                "Prince"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        //return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-header", "Princess")
                .body(student);

    }

    @GetMapping("students")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Tuyi","Shime"));
        students.add(new Student(2, "Kai","tare"));
        students.add(new Student(3,"John","Paul"));
        return ResponseEntity.ok(students);
    }

    //Spring BOOT API with Path Variable
    //{id} -- means URI template variable ex:
    //http://localhost:8080/students/1/kaitare/prince
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int StudentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName){
          Student student = new Student(StudentId,lastName,firstName);
          return ResponseEntity.ok(student);
    }

    // Springboot Request API with request parameter.
    //http://localhost:8080/students/query?id=1&firstName=Kaitare&lastName=Prince
    // this is a querry parameter

    @GetMapping("students/query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName
    ){
        Student student = new Student(id, firstName,lastName);
        return ResponseEntity.ok(student);

    }

    //Spring boot REST API that handles HTTP POST Request - Create a new Resource

    //@PostMapping and @RequestBody

    @PostMapping("students/create")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }


    //SpringBoot REST API that handles http PUT request that updates the existing
    @PutMapping("Students/{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return  ResponseEntity.ok(student);
    }

    //SpringBOOT REST API that handles the http DELETE Request - Deleting the existing resource

    @DeleteMapping("student/{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int  StudentId){
        System.out.println(StudentId );
        return ResponseEntity.ok("StudentDeleted Successfully");
    }

}
