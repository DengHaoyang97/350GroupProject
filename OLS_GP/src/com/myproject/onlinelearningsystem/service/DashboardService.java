package com.myproject.onlinelearningsystem.service;

import com.myproject.onlinelearningsystem.model.Admin;
import com.myproject.onlinelearningsystem.model.Staff;
import com.myproject.onlinelearningsystem.model.Student;
import com.myproject.onlinelearningsystem.model.Subject;
import com.myproject.onlinelearningsystem.repository.AdminRepository;
import com.myproject.onlinelearningsystem.repository.StaffRepository;
import com.myproject.onlinelearningsystem.repository.StudentRepository;
import com.myproject.onlinelearningsystem.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public Admin getAdminById(String id) {
        Optional<Admin> Admin = adminRepository.findById(id);
        return Admin.orElse(null);
    }

    public Subject getSubjectById(String id) {
        Optional<Subject> Subject = subjectRepository.findById(id);
        return Subject.orElse(null);
    }

    public Student getStudentById(String id) {
        Optional<Student> Student = studentRepository.findById(id);
        return Student.orElse(null);
    }

    public Student getStudentByIdAndName(String id, String name) {
        Optional<Student> Student = studentRepository.findStudentByIdAndName(id, name);
        return Student.orElse(null);
    }

    public Staff getStaffById(String id) {
        Optional<Staff> Staff = staffRepository.findById(id);
        return Staff.orElse(null);
    }

    public Student updateStudentInfo(String id, String passportNumber, LocalDate dateOfBirth, String email, String password) {
        System.out.println("get prater= " + id + "|" + passportNumber + "|" + dateOfBirth);
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setPassportNumber(passportNumber);
            student.setDateOfBirth(dateOfBirth);
            student.setEmail(email);
            student.setPassword(password);
            student.checked=true;
            studentRepository.save(student);
        }
        return student;
    }


    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    public List<Staff> findStaffByName(String name) {
        return staffRepository.findByName(name);
    }

    public List<Student> findStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<Subject> findSubjectByName(String name) {
        return subjectRepository.findByName(name);
    }

    public Student findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    public Student findStudentByPassportNumber(String passportNumber) {
        return studentRepository.findByPassportNumber(passportNumber);
    }

    public Student createStudent(String name) {
        Student student = new Student(name);
        return studentRepository.save(student);
    }
}
