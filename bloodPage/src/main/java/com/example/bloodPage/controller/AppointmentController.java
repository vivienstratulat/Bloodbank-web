package com.example.bloodPage.controller;

import com.example.bloodPage.email.EmailService;
import com.example.bloodPage.entity.Appointment;
import com.example.bloodPage.entity.Doctor;
import com.example.bloodPage.entity.Donor;
import com.example.bloodPage.service.AppointmentService;
import com.example.bloodPage.service.CenterService;
import com.example.bloodPage.service.DoctorService;
import com.example.bloodPage.service.DonorService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final DonorService donorService;
    private final DoctorService doctorService;
    private final EmailService emailService;
    private final CenterService centerService;

   public AppointmentController(AppointmentService appointmentService, DonorService donorService, DoctorService doctorService, EmailService emailService, CenterService centerService) {
      this.appointmentService = appointmentService;
       this.donorService = donorService;
       this.doctorService = doctorService;

       this.emailService = emailService;
       this.centerService = centerService;
   }

    @PostMapping("/create")
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment){
        appointmentService.saveAppointment(appointment);
        emailService.sendEmail(appointment.getDonor().getEmail(),appointment.getDonor().getFirstName(),appointment.getDonor().getLastName(),appointment.getDatetime(),appointment.getCenter().getName(),"Bubu","Cupi");
        //System.out.println("Actual capacity:"+appointment.getCenter().capacity);
        centerService.updateCapacity(appointment.getCenter().getId());
        return ResponseEntity.ok(appointment);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Appointment>> getAllAppointments(){
        List<Appointment> allAppointments = appointmentService.findAllAppointments();

        return ResponseEntity.ok(allAppointments);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Appointment>> getAppointmentByEmail(@PathVariable String email){
       Donor donor= donorService.getDonorByEmail(email);
        List<Appointment> foundAppointments = appointmentService.findUserAppointments(donor);
        return ResponseEntity.ok(foundAppointments);
    }


    @GetMapping("doctor/{email}")
    public ResponseEntity<List<Appointment>> getAppointmentByDoctorEmail(@PathVariable String email){
        Doctor doctor= doctorService.getDoctorByEmail(email);
        List<Appointment> foundAppointments = appointmentService.findAllDoctorAppointments(doctor);
        return ResponseEntity.ok(foundAppointments);
    }


    @GetMapping("/getAppointmentsToday/{email}")
    public ResponseEntity<List<Appointment>> getAppointmentsToday(@PathVariable String email){
        Doctor doctor= doctorService.getDoctorByEmail(email);
        List<Appointment> foundAppointments = appointmentService.findAllDoctorAppointmentToday(doctor);
        return ResponseEntity.ok(foundAppointments);
    }

   /*// @GetMapping("/send-email/{to}")
   // @EventListener(ApplicationReadyEvent.class)
    public String sendEmail(){
        emailService.sendEmail("stratulatvivienn@gmail.com","hellooooooooooooooooo");
        return "Email sent successfully";
    }*/





}
