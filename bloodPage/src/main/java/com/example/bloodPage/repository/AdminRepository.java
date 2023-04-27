package com.example.bloodPage.repository;

import com.example.bloodPage.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID>
{
    Admin findAdminById(UUID id);
    Admin findAdminByEmail(String email);
    void deleteByEmail(String email);
}
