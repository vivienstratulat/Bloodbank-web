package com.example.bloodPage.controller;

import com.example.bloodPage.entity.Appointment;
import com.example.bloodPage.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/createAppointment")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment){
        appointmentService.saveAppointment(appointment);
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        List<Appointment> allAppointments = appointmentService.findAllAppointments();
        return ResponseEntity.ok(allAppointments);
    }

}
