package com.myproject.onlinelearningsystem.service;

import com.myproject.onlinelearningsystem.repository.StaffRepository;
import com.myproject.onlinelearningsystem.model.Staff;
import com.myproject.onlinelearningsystem.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }
    public Staff createStaff(String name, String passportNumber, String status, String email, String password) {
        Staff Staff = new Staff(name, passportNumber, status, email ,password);

        return staffRepository.save(Staff);
    }
    public Staff getStaffById(String id) {
        Optional<Staff> Staff = staffRepository.findById(id);
        return Staff.orElse(null);
    }
    public Staff updateStaffInfo(String id, String[] Subject) {
        Staff Staff = staffRepository.findById(id).orElse(null);
        if (Staff != null) {
            Staff.setSubject(Subject);
            staffRepository.save(Staff);
        }
        return Staff;
    }

    public List<Staff> findByName(String name){
        return staffRepository.findByName(name);
    };

    public Staff findByEmail(String email){
        return staffRepository.findByEmail(email);
    };
}
