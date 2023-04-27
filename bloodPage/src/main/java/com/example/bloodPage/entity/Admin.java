package com.example.bloodPage.entity;


import jakarta.persistence.Entity;

import javax.persistence.Table;

@Entity(name = "Admin")
@Table(name = "app_admin")

public class Admin extends User{

        public Admin() {
        }

        public Admin(String email, String password, String firstName, String lastName, String role) {
            super(email, password, firstName, lastName, role);
        }
}
