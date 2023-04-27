package com.example.bloodPage.controller;

import com.example.bloodPage.entity.Doctor;
import com.example.bloodPage.entity.Donor;
import com.example.bloodPage.service.DoctorService;
import com.example.bloodPage.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping("/register")
    ResponseEntity<Doctor> registerDoctor(@RequestBody Doctor doctor){
        Doctor newDoctor = doctorService.registerDoctor(doctor);
        return ResponseEntity.ok(newDoctor);
    }

    @GetMapping("/getAll")
    ResponseEntity<List<Doctor>> getAllDoctors(){
        List<Doctor> allDoctors = doctorService.getDoctors();
        return ResponseEntity.ok(allDoctors);
    }

    @PostMapping("/delete/{email}")
    void deleteDoctor(@PathVariable("email") String email) {
        doctorService.deleteDoctorByEmail(email);
    }

    @GetMapping("/{email}")
    ResponseEntity<Doctor> getDoctorByEmail(@PathVariable String email) {
        Doctor foundDoctor = doctorService.getDoctorByEmail(email);
            return ResponseEntity.ok(foundDoctor);

    }

    @PostMapping("/update/{email}")
    ResponseEntity<Doctor> updateDoctor(@PathVariable String  email,@RequestBody Doctor doctor){
        Doctor updatedDoctor = doctorService.updateDoctor(email,doctor);
        if (updatedDoctor != null)
            return ResponseEntity.ok(updatedDoctor);
        else
            return (ResponseEntity<Doctor>) ResponseEntity.notFound();
    }




}
