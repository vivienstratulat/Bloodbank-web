package com.example.bloodPage.service;

import com.example.bloodPage.entity.Admin;
import com.example.bloodPage.entity.Doctor;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    Doctor registerDoctor(Doctor doctor);
    void deleteDoctorByEmail(String email);
    Doctor updateDoctor( String email,Doctor newDoctor);
    Admin getAdmin(UUID userID);
    List<Doctor> getDoctors();
    Admin getAdminByEmail(String email);
    Admin registerAdmin(Admin admin);
}
