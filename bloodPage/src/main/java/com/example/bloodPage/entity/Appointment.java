package com.example.bloodPage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "app_appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    public Donor donor;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    public Doctor doctor;

   @ManyToOne
    @JoinColumn(name = "center_id")
    public Center center;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate date;
    public String confirmed;
    public Appointment() {
    }

    public LocalDate getDatetime() {
        return date;
    }

    public void setDatetime(LocalDate datetime) {
        this.date = datetime;
    }

    public String isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

   public Appointment(Donor donor, Doctor doctor, Center center, LocalDate datetime,String confirmed) {
        this.donor = donor;
        this.doctor = doctor;
        this.center = center;
        this.date = datetime;
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
