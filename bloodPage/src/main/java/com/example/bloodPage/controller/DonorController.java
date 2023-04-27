package com.example.bloodPage.controller;

import com.example.bloodPage.entity.Donor;
import com.example.bloodPage.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/donor")
@CrossOrigin(origins = "http://localhost:3000")
public class DonorController {
    private final DonorService donorService;

    public DonorController(DonorService donorService) {
        this.donorService = donorService;
    }

    @PostMapping("/register")
    ResponseEntity<Donor> registerDonor(@RequestBody Donor donor){
        Donor newDonor = donorService.registerDonor(donor);
        return ResponseEntity.ok(newDonor);
    }


    @GetMapping("/getAll")
    ResponseEntity<List<Donor>> getAllDonors(){
        List<Donor> allDonors = donorService.getDonors();
        return ResponseEntity.ok(allDonors);
    }

    @GetMapping("/{email}")
    ResponseEntity<Donor> getDonorByEmail(@PathVariable String email) throws AuthenticationException {
        Donor foundDonor = donorService.getDonorByEmail(email);
        if (foundDonor != null)
            return ResponseEntity.ok(foundDonor);
        else
            throw new AuthenticationException("Donor not found "+email);
    }

    @PostMapping("/update/{email}")
    ResponseEntity<Donor> updateDonor(@PathVariable String email,@RequestBody Donor donor){
        Donor updatedDonor = donorService.updateDonor(email,donor);
        if (updatedDonor != null)
            return ResponseEntity.ok(updatedDonor);
        else
            return (ResponseEntity<Donor>) ResponseEntity.notFound();
    }

    @PostMapping("/delete/{email}")
    void deleteDonor(@PathVariable String email){
         donorService.deleteDonorByEmail(email);

    }


    @GetMapping("getById/{id}")
    ResponseEntity<Donor> getDonorById(@PathVariable UUID id) throws AuthenticationException {
        Donor foundDonor = donorService.getDonorById(id);
        if (foundDonor != null)
            return ResponseEntity.ok(foundDonor);
        else
            throw new AuthenticationException("Donor not found");
    }



}
