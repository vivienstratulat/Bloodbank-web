package com.example.bloodPage.service.impl;

import com.example.bloodPage.entity.Donor;
import com.example.bloodPage.entity.User;
import com.example.bloodPage.repository.DonorRepository;
import com.example.bloodPage.service.DonorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DonorServiceImpl implements DonorService {
    private final DonorRepository donorRepository;

    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @Override
    public Donor registerDonor(Donor donor) {
        if(donorRepository.existsByEmail(donor.getEmail()))
            throw new RuntimeException("This email is already taken");
        Donor u=new Donor();
        u.setEmail(donor.email);
        u.setPassword(donor.password);
        u.setFirstName(donor.firstName);
        u.setLastName(donor.lastName);
        u.setRole(donor.role);
        u.setBloodType(donor.bloodType);
        u.setLocation(donor.location);
       Donor registeredDonor = donorRepository.save(u);
         return registeredDonor;
    }

    @Override
    public Donor getDonorByEmail(String email) {
       Optional<Donor> foundDonor = donorRepository.findByEmail(email);
       if(foundDonor.isPresent()){
           return foundDonor.get();
       }
       else return null;
    }

    @Override
    @Transactional
    public void deleteDonorByEmail(String email) {
        donorRepository.deleteByEmail(email);

    }

    @Override
    public Donor updateDonor(String email,Donor newDonor) {
        Donor donor = donorRepository.findByEmail(email).get();
        if (donor != null) {
            donor.setEmail(newDonor.getEmail());
            donor.setPassword(newDonor.getPassword());
            donor.setFirstName(newDonor.getFirstName());
            donor.setLastName(newDonor.getLastName());
            donor.setBloodType(newDonor.getBloodType());
            donor.setLocation(newDonor.getLocation());

        }
        else System.out.println("couldn t find");
        return donorRepository.save(donor);
    }


    @Override
    public Donor getDonorById(UUID id) {
        Optional<Donor> donor = donorRepository.findById(id);
        if(donor.isPresent()){
            return donor.get();
        }
        else return null;
    }

    @Override
    public List<Donor> getDonors() {
        return donorRepository.findAll();
    }
}
