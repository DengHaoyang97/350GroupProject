package com.myproject.onlinelearningsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Staff {
    @Id
    private String id;
    private String name;
    private String passportNumber;
    private String[] subject;

    private String password;
    private String status;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String[] getSubject() {
        return subject;
    }

    public void setSubject(String[] subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Constructor getter  setter
    public Staff(String name, String passportNumber, String status, String email, String password) {
        this.name = name;
        this.passportNumber = passportNumber;
        this.status = status;
        this.email = email;
        this.password = password;
        //ID Generator
        this.id = String.format("%04d", (int) (Math.random() * 999999));
    }
    public Staff() {
    }
    // getter  setter
}
