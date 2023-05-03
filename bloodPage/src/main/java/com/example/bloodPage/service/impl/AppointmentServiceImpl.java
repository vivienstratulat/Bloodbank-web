package com.example.bloodPage.service.impl;

import com.example.bloodPage.entity.Appointment;
import com.example.bloodPage.entity.Doctor;
import com.example.bloodPage.entity.Donor;
import com.example.bloodPage.repository.AppointmentRepository;
import com.example.bloodPage.repository.CenterRepository;
import com.example.bloodPage.repository.DonorRepository;
import com.example.bloodPage.service.AppointmentService;
import com.example.bloodPage.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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


    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, DonorRepository donorRepository, CenterRepository centerRepository, DoctorServiceImpl doctorServiceImpl, DonorServiceImpl donorServiceImpl) {
        this.appointmentRepository = appointmentRepository;
        this.donorRepository=donorRepository;
        this.centerRepository=centerRepository;
        this.doctorServiceImpl = doctorServiceImpl;
        this.donorServiceImpl = donorServiceImpl;


    }


    @Override
    @Transactional
    public void saveAppointment(Appointment appointment) {
        if(appointment.getCenter().capacity==0)
            throw new RuntimeException("This center is full");
        Appointment newAppointment=new Appointment();
        newAppointment.setDatetime(appointment.getDatetime());
        newAppointment.setCenter(appointment.getCenter());

        newAppointment.setDonor(appointment.getDonor());
        newAppointment.setConfirmed("not confirmed");

        Doctor doctor= doctorServiceImpl.findDoctorWithMinAppointments();
        newAppointment.setDoctor(doctor);
        appointmentRepository.save(newAppointment);


    }

    @Override
    public List<Appointment> findUserAppointments(Donor donor) {
        return appointmentRepository.findUserAppointments(donor);
    }

    @Override
    public void deleteUserAppointments(Donor donor) {
       List<Appointment> appointments=appointmentRepository.findUserAppointments(donor);
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
        List<Appointment> appointments=appointmentRepository.findAll();
        return appointments;
    }

    @Override
    public List<Appointment> findAllDoctorAppointmentToday(Doctor doctor) {
        List<Appointment> foundAppointments=appointmentRepository.findAppointmentsByDoctor(doctor);
        for(Appointment appointment:foundAppointments){
            if(appointment.getDatetime().getDay()!=LocalDateTime.now().getDayOfMonth())
                foundAppointments.remove(appointment);
        }
       // foundAppointments.stream().filter(appointment -> appointment.getDatetime().getDay()==(LocalDateTime.now().getDayOfMonth()));
        return foundAppointments;
    }
}
