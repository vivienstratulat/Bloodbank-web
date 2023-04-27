package com.example.bloodPage.service.impl;

import com.example.bloodPage.entity.Admin;
import com.example.bloodPage.entity.Doctor;
import com.example.bloodPage.entity.Donor;
import com.example.bloodPage.repository.AdminRepository;
import com.example.bloodPage.repository.DoctorRepository;
import com.example.bloodPage.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final DoctorRepository doctorRepository;

    public AdminServiceImpl(AdminRepository adminRepository, DoctorRepository doctorRepository) {
        this.adminRepository = adminRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Admin registerAdmin(Admin admin) {
        Admin adm= adminRepository.save(admin);
        return adm;
    }


    @Override
    public Doctor registerDoctor(Doctor doctor) {
        Doctor doc= doctorRepository.save(doctor);
        return doc;
    }

    @Override
    @Transactional
    public void deleteDoctorByEmail(String email) {
       doctorRepository.deleteByEmail(email) ;

    }

    @Override
    public Doctor updateDoctor(String email, Doctor newDoctor) {
        Doctor doctor = doctorRepository.findByEmail(email).get();
        if (doctor != null) {
            doctor.setEmail(newDoctor.getEmail());
            doctor.setPassword(newDoctor.getPassword());
            doctor.setFirstName(newDoctor.getFirstName());
            doctor.setLastName(newDoctor.getLastName());
            doctor.setCenter(newDoctor.getCenter());

        }
        else System.out.println("couldn t find");
        return doctorRepository.save(doctor);

    }

    @Override
    public Admin getAdmin(UUID userID) {
        Optional<Admin> foundAdmin= adminRepository.findById(userID);
        if(foundAdmin.isPresent()){
            return foundAdmin.get();
        }
        else return null;
    }

    @Override
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Admin getAdminByEmail(String email) {
        return adminRepository.findAdminByEmail(email);
    }
}
