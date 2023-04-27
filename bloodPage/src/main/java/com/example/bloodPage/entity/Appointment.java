package com.example.bloodPage.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "app_appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public Donor donor;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    public Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "center_id")
    public Center center;

    public Date datetime;
    boolean confirmed;
    public Appointment() {
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Appointment(Donor donor, Doctor doctor, Center center, Date datetime, boolean confirmed) {
        this.donor = donor;
        this.doctor = doctor;
        this.center = center;
        this.datetime = datetime;
        this.confirmed = confirmed;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }
}
