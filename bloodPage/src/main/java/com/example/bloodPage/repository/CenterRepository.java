package com.example.bloodPage.repository;

import com.example.bloodPage.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CenterRepository extends JpaRepository<Center, UUID> {
    Optional<Center> findById(UUID id);

}
