package com.example.bloodPage.controller;

import com.example.bloodPage.entity.Center;
import com.example.bloodPage.entity.Doctor;
import com.example.bloodPage.service.CenterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/center")
public class CenterController {
    private final CenterService centerService;

    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @GetMapping("/getAll")
    ResponseEntity<List<Center>> getAllCenters(){
        List<Center> allCenters = centerService.getCenters();
        return ResponseEntity.ok(allCenters);
    }

    @PostMapping("/register")
    ResponseEntity<Center> registerCenter(@RequestBody Center center){
        Center newCenter = centerService.addCenter(center);
        return ResponseEntity.ok(newCenter);
    }

    @GetMapping("/{id}")
    ResponseEntity<Center> getCenterById(@PathVariable UUID id){
        Optional<Center> foundCenter = centerService.getCenterById(id);
        if (foundCenter.isPresent())
            return ResponseEntity.ok(foundCenter.get());
        else
            return (ResponseEntity<Center>) ResponseEntity.notFound();
    }
}
