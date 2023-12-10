package com.myproject.onlinelearningsystem.controller;

import com.myproject.onlinelearningsystem.model.Subject;
import com.myproject.onlinelearningsystem.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<Subject> getAllSubject() {
        return subjectService.getAllSubject();
    }


    @PostMapping("/add")
    public ResponseEntity<Subject> addSubject(@RequestParam String name) {
        Subject subject = subjectService.createsubject(name);
        return ResponseEntity.ok(subject);
    }


}
