package com.example.bloodPage.service;

import com.example.bloodPage.entity.Doctor;

import java.util.List;
import java.util.UUID;

public interface DoctorService {
    Doctor registerDoctor(Doctor doctor);
    Doctor getDoctorByEmail(String email);
    void deleteDoctorByEmail(String email);
    Doctor updateDoctor(String email, Doctor newDoctor);
    Doctor getDoctorById(UUID id);
    List<Doctor> getDoctors();
    public Doctor findDoctorWithMinAppointments();



}
