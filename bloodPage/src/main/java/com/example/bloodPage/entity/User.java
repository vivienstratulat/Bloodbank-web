package com.example.bloodPage.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "app_user")
@Inheritance(strategy =InheritanceType.JOINED)
public class User {
    @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
  public  UUID id;
    public String email;
    public String password;
    public  String firstName;
    public String lastName;
    public String role;

    public User() {
    }

    public User( String email, String password, String firstName, String lastName, String role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
