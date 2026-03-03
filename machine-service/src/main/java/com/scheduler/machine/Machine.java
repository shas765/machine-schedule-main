package com.scheduler.machine;

import jakarta.persistence.*;

@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    private String status;

    public Machine() {}

    public Machine(String name, String type,String status) {
        this.name = name;
        this.type = type;
        this.status = status != null ? status : "ACTIVE";
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getStatus() { return status; }

    public void setName(String name) { this.name = name; }
    public void setType(String type) { this.type = type; }
    public void setStatus(String status) { this.status = status; }
}