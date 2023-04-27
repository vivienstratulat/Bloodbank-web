package com.example.bloodPage.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Donor")
@Table(name = "app_donor")

public class Donor extends User{
    public String bloodType;
    public String location;

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @OneToMany
    @JoinColumn(name = "user_id")
    public List<Appointment> appointments = new ArrayList<>();



    public Donor() {
    }

    public Donor(String email, String password, String firstName, String lastName, String role, String bloodType,String location) {
        super(email, password, firstName, lastName, role);
        this.bloodType = bloodType;
        this.location = location;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Transactional
    public void voidAddAppointment(Appointment appointment){
        appointments.add(appointment);
    }

    @Transactional
    public void voidRemoveAppointment(Appointment appointment){
        appointments.remove(appointment);
    }

}
