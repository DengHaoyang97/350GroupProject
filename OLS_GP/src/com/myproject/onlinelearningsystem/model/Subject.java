package com.myproject.onlinelearningsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Subject {
    @Id
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Subject(String name) {
        this.name = name;

        this.id = String.format("%06d", (int) (Math.random() * 999999));
    }
}
