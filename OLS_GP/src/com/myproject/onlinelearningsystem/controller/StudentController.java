package com.myproject.onlinelearningsystem.controller;

import com.myproject.onlinelearningsystem.model.Staff;
import com.myproject.onlinelearningsystem.model.Student;
import com.myproject.onlinelearningsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllUsers() {
        return studentService.getAllStudent();
    }

    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestParam String name) {
        Student student = studentService.createStudent(name);
        return ResponseEntity.ok(student);
    }

    @PostMapping("/first_login")
    public ResponseEntity<String> First_login(@RequestParam String name, @RequestParam String id) {
        Student student = studentService.getStudentById(id);
        if (student != null && student.getName().equals(name)) {
            return ResponseEntity.ok("Student First Time logged in successfully. ID: " + id);
        }
        return ResponseEntity.status(401).body("Invalid name or ID");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String id, @RequestParam String password) {
        Student student = studentService.getStudentById(id);
        if ( student != null &&student.getPassword().equals(password)) {
            return ResponseEntity.ok("Student logged in successfully. ID: " + id);
        }
        return ResponseEntity.status(401).body("Invalid name or ID");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudentInfo(
            @PathVariable String id,
            @RequestParam String passportNumber,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
            @RequestParam String email,
            @RequestParam String password) {
        Student updatedStudent = studentService.updateStudentInfo(id, passportNumber, dateOfBirth, email, password);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/updateSub/{id}")
    public ResponseEntity<Student> updateStaffInfo(
            @PathVariable String id,
            @RequestParam String[] Subject
    ) {
        Student updatedStudent = studentService.updateSubjectInfo(id, Subject);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);}
        return ResponseEntity.notFound().build();
    }



    @GetMapping("/search")
    public List<Student> findByName(@RequestParam String name){
        return studentService.findByName(name);};

    @GetMapping("/search_email")
    public Student findByEmail(@RequestParam String email){
        return studentService.findByEmail(email);
    }
}
