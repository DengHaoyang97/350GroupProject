package com.myproject.onlinelearningsystem.service;


import com.myproject.onlinelearningsystem.model.Staff;
import com.myproject.onlinelearningsystem.model.Subject;
import com.myproject.onlinelearningsystem.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject createsubject(String name) {
        Subject subject = new Subject(name);
        return subjectRepository.save(subject);}

    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

}
