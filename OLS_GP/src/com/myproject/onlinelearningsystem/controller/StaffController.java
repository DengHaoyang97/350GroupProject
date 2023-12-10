package com.myproject.onlinelearningsystem.controller;

import com.myproject.onlinelearningsystem.model.Staff;
import com.myproject.onlinelearningsystem.model.Admin;
import com.myproject.onlinelearningsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @GetMapping
    public List<Staff> getAllUsers() {
        return staffService.getAllStaff();
    }



    @PostMapping("/register")
    public ResponseEntity<Staff> registerStaff(
            @RequestParam String name,
            @RequestParam String passportNumber,
            @RequestParam String status,
            @RequestParam String email,
            @RequestParam String password
    ) {
        Staff Staff = staffService.createStaff(name, passportNumber, status, email, password);
        return ResponseEntity.ok(Staff);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String id, @RequestParam String password) {
        Staff Staff = staffService.getStaffById(id);
        if (Staff != null && Staff.getPassword().equals(password)) {
            return ResponseEntity.ok("Staff logged in successfully. ID: " + id);
        }
        return ResponseEntity.status(401).body("Invalid name or ID");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Staff> updateStaffInfo(
            @PathVariable String id,
            @RequestParam String[] Subject
    ) {
        Staff updatedStaff = staffService.updateStaffInfo(id, Subject);
        if (updatedStaff != null) {
            return ResponseEntity.ok(updatedStaff);}
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Staff> findByName(@RequestParam String name){
        return staffService.findByName(name);};

    @GetMapping("/search_email")
    public Staff findByEmail(@RequestParam String email){
        return staffService.findByEmail(email);
    }
}
