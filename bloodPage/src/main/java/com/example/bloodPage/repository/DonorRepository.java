package com.example.bloodPage.repository;

import com.example.bloodPage.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DonorRepository extends JpaRepository<Donor, UUID> {
    Optional<Donor> findByEmail(String email);
    Boolean existsByEmail(String email);
    void deleteByEmail(String email);


}
