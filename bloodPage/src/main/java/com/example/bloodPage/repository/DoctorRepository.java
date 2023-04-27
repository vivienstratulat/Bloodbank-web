package com.example.bloodPage.repository;

import com.example.bloodPage.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Optional<Doctor> findByEmail(String email);

  void deleteByEmail(String email);
  Doctor findDoctorById(UUID id);
  void deleteDoctorById(UUID id);
  //void deleteDoctorByEmail(String email);

    Boolean existsByEmail(String email);
}
