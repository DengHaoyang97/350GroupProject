package com.myproject.onlinelearningsystem.repository;

import com.myproject.onlinelearningsystem.model.Student;
import com.myproject.onlinelearningsystem.model.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubjectRepository extends MongoRepository<Subject, String> {
    List<Subject> findByName(String name);
}
