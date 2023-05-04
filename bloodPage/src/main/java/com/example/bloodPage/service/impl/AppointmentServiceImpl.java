package com.example.bloodPage.service.impl;

import com.example.bloodPage.email.EmailService;
import com.example.bloodPage.entity.Appointment;
import com.example.bloodPage.entity.Doctor;
import com.example.bloodPage.entity.Donor;
import com.example.bloodPage.repository.AppointmentRepository;
import com.example.bloodPage.repository.CenterRepository;
import com.example.bloodPage.repository.DonorRepository;
import com.example.bloodPage.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public  class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private final AppointmentRepository appointmentRepository;
    @Autowired
    private final DonorRepository donorRepository;
    @Autowired
    private final CenterRepository centerRepository;
    private final DoctorServiceImpl doctorServiceImpl;
    @Autowired
    private final DonorServiceImpl donorServiceImpl;

    @Autowired
    private final EmailService emailService;


    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DonorRepository donorRepository, CenterRepository centerRepository, DoctorServiceImpl doctorServiceImpl, DonorServiceImpl donorServiceImpl, EmailService emailService) {
        this.appointmentRepository = appointmentRepository;
        this.donorRepository = donorRepository;
        this.centerRepository = centerRepository;
        this.doctorServiceImpl = doctorServiceImpl;
        this.donorServiceImpl = donorServiceImpl;


        this.emailService = emailService;
    }


    @Override
    @Transactional
    public void saveAppointment(Appointment appointment) {
        if (appointment.getCenter().capacity == 0)
            throw new RuntimeException("This center is full");

        Appointment newAppointment = new Appointment();
        newAppointment.setDatetime(appointment.getDatetime());
        newAppointment.setCenter(appointment.getCenter());

        newAppointment.setDonor(appointment.getDonor());
        newAppointment.setConfirmed("not confirmed");

        Doctor doctor = doctorServiceImpl.findDoctorWithMinAppointments();
        newAppointment.setDoctor(doctor);
        appointmentRepository.save(newAppointment);


    }

    @Override
    public List<Appointment> findUserAppointments(Donor donor) {
        return appointmentRepository.findUserAppointments(donor);
    }

    @Override
    public void deleteUserAppointments(Donor donor) {
        List<Appointment> appointments = appointmentRepository.findUserAppointments(donor);
        appointmentRepository.deleteAll(appointments);

    }

    @Override
    public void deleteAppointmentById(UUID id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public Appointment findAppointmentById(UUID id) {
        return appointmentRepository.findById(id).get();
    }

    @Override
    public List<Appointment> findAllDoctorAppointments(Doctor doctor) {
        return appointmentRepository.findAppointmentsByDoctor(doctor);
    }

    @Override
    public List<Appointment> findAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments;
    }

    @Override
    public List<Appointment> findAllDoctorAppointmentToday(Doctor doctor) {
        List<Appointment> foundAppointments = appointmentRepository.findAppointmentsByDoctor(doctor);
        for (Appointment appointment : foundAppointments) {
            if (appointment.getDatetime().getDayOfMonth() != LocalDateTime.now().getDayOfMonth())
                foundAppointments.remove(appointment);
        }
        // foundAppointments.stream().filter(appointment -> appointment.getDatetime().getDay()==(LocalDateTime.now().getDayOfMonth()));
        return foundAppointments;
    }

    /*@Override
    public List<Appointment> findAppointmentsByDate(LocalDate Date) {
        List<Appointment> appointments = appointmentRepository.findAll();
        for (Appointment appointment : appointments) {
            if (appointment.getDatetime().getDayOfMonth() != Date.getDayOfMonth()) {
                appointments.remove(appointment);
            }
        }
        for(Appointment appointment:appointments){
            System.out.println(appointment.getDatetime());
        }
            return appointments;
        }
*/


    @Scheduled(cron = "0 52 01 * * *",zone="Europe/Bucharest")
    @Async
    public void sendAppointmentReminders() {
        System.out.println("REMINDER");

        List<Appointment> appointments = appointmentRepository.findByDate(LocalDate.now().plusDays(1));

        for (Appointment appointment : appointments) {
            emailService.sendReminder(appointment);

        }
        if (appointments.size() == 0)
            System.out.println("no appointments tomorrow");
    }
}
