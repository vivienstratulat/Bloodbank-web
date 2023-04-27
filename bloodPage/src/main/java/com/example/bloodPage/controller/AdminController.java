package com.example.bloodPage.controller;

import com.example.bloodPage.entity.Admin;
import com.example.bloodPage.entity.Doctor;
import com.example.bloodPage.repository.AdminRepository;
import com.example.bloodPage.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    private final AdminService adminService;


    public AdminController(AdminService adminService) {
        this.adminService = adminService;

    }


    @PostMapping("/register")
    ResponseEntity<Admin> registerAmin(@RequestBody Admin admin){
        Admin newAdmin = adminService.registerAdmin(admin);
        return ResponseEntity.ok(newAdmin);
    }

    @GetMapping("/{email}")
    ResponseEntity<Admin> getAdmin(@PathVariable("email") String email) {
        Admin foundAdmin = adminService.getAdminByEmail(email);
        if (foundAdmin != null)
            return ResponseEntity.ok(foundAdmin);
        else
            return (ResponseEntity<Admin>) ResponseEntity.notFound();

    }

    @DeleteMapping ("/delete/{email}")
    void deleteDoctor(@PathVariable("email") String email) {
        adminService.deleteDoctorByEmail(email);
    }

    @PostMapping("/update/{email}")
    ResponseEntity<Doctor> updateDoctor(@PathVariable String  email,@RequestBody Doctor doctor){
        Doctor updatedDoctor = adminService.updateDoctor(email,doctor);
        if (updatedDoctor != null)
            return ResponseEntity.ok(updatedDoctor);
        else
            return (ResponseEntity<Doctor>) ResponseEntity.notFound();
    }


}
