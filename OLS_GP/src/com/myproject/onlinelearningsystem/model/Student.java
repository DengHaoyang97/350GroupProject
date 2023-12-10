package com.myproject.onlinelearningsystem.model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Student {
    @Id
    private String id;

    private String password;

    private String[] subject;
    private String name;
    private String passportNumber;
    private LocalDate dateOfBirth;
    public boolean checked=false;
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public Student(String name) {
        this.name = name;

        this.id = String.format("%06d", (int) (Math.random() * 999999));
    }
    public Student() {
    }
    public void update(String passportNumber, LocalDate dateOfBirth, String password) {
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public String[] getSubject() {
        return subject;
    }


    public void setSubject(String[] subject) {
        this.subject = subject;
    }

}


