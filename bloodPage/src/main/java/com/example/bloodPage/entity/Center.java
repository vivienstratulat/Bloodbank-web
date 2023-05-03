package com.example.bloodPage.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity(name = "center")

public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID id;
    public String name;

    public int capacity;

    public Center() {
    }

    public Center(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void decrementCapacity(){
        this.capacity--;
    }
}

