package com.example.bloodPage.service;

import com.example.bloodPage.entity.Doctor;
import com.example.bloodPage.entity.Donor;

import java.util.List;
import java.util.UUID;

public interface DonorService {
    Donor registerDonor(Donor donor);
    Donor getDonorByEmail(String email);
    void deleteDonorByEmail(String email);
    Donor updateDonor(String email,Donor newDonor);
    Donor getDonorById(UUID id);
    List<Donor> getDonors();
}
