package com.myproject.onlinelearningsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Admin {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
    private String role; // "administrator"

    public Admin(String name, String email, String password, String role) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.role = role;
        // 生成一个唯一的4位数字ID
        this.id = String.format("%04d", (int) (Math.random() * 999999));
    }

    public Admin() {
    }
    // 构造函数、getters 和 setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
