package com.myproject.onlinelearningsystem.service;

import com.myproject.onlinelearningsystem.model.Student;
import com.myproject.onlinelearningsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }
    public Student createStudent(String name) {
        Student student = new Student(name);
        return studentRepository.save(student);
    }
    public Student getStudentById(String id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }


    public Student updateStudentInfo(String id, String passportNumber, LocalDate dateOfBirth, String email, String password) {
        System.out.println("get prater= " + id + "|" + passportNumber + "|" + dateOfBirth);
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setPassportNumber(passportNumber);
            student.setDateOfBirth(dateOfBirth);
            student.setEmail(email);
            student.setPassword(password);
            studentRepository.save(student);
        }
        return student;
    }

    public Student updateSubjectInfo(String id, String[] subject) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setSubject(subject);
            studentRepository.save(student);
        }
        return student;
    }

    public List<Student> findByName(String name){
        return studentRepository.findByName(name);
    };

    public Student findByEmail(String email){
        return studentRepository.findByEmail(email);
    };
}
