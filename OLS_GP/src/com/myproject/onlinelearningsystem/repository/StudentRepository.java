package com.myproject.onlinelearningsystem.repository;

import com.myproject.onlinelearningsystem.model.Admin;
import com.myproject.onlinelearningsystem.model.Staff;
import com.myproject.onlinelearningsystem.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends MongoRepository<Student, String> {

    Optional<Student> findById(String id);
    List<Student> findByName(String name);

    Student findByEmail(String email);
    Optional<Student> findStudentByIdAndPassword(String id, String password);

    Optional<Student> findStudentByIdAndName(String id, String Name);

    Student findByPassportNumber(String passportNumber);


}
