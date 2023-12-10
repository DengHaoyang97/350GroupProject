package com.myproject.onlinelearningsystem.controller;

import com.myproject.onlinelearningsystem.model.Admin;
import com.myproject.onlinelearningsystem.model.Staff;
import com.myproject.onlinelearningsystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @PostMapping("/register")
    public Admin createUser(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String role) {
        return adminService.createUser(name, email, password, role);
    }

    @GetMapping
    public List<Admin> getAllUsers() {
        return adminService.getAllUsers();
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String id, @RequestParam String password, HttpServletResponse response) {
        Admin admin = adminService.getAdminById(id);
        if (admin != null && admin.getPassword().equals(password)) {
            // 登录成功，设置 Cookie
            Cookie cookie = new Cookie("OLS", admin.getId()); // 实际情况下应生成唯一会话ID
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            response.addCookie(cookie);

            return ResponseEntity.ok("User logged in successfully");
        } else {
            // 登录失败
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("OLS", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
            return ResponseEntity.ok("User logout in successfully");
    }


}
