package com.myproject.onlinelearningsystem.service;

import com.myproject.onlinelearningsystem.model.Admin;
import com.myproject.onlinelearningsystem.model.Staff;
import com.myproject.onlinelearningsystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin createUser(String name, String email, String password, String role) {
        Admin admin = new Admin(name, email, password, role);
        return adminRepository.save(admin);
    }

    public List<Admin> getAllUsers() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(String id) {
        Optional<Admin> Admin = adminRepository.findById(id);
        return Admin.orElse(null);
    }

    public void logout(String id) {}
    // 更新、删除等方法
}
