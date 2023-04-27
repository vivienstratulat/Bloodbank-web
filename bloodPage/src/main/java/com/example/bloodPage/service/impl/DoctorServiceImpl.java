package com.example.bloodPage.service.impl;

import com.example.bloodPage.entity.Appointment;
import com.example.bloodPage.entity.Doctor;
import com.example.bloodPage.repository.AppointmentRepository;
import com.example.bloodPage.repository.DoctorRepository;
import com.example.bloodPage.service.DoctorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;


    public DoctorServiceImpl(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;


    }

    @Override
    public Doctor registerDoctor(Doctor doctor) {
        if(doctorRepository.existsByEmail(doctor.getEmail()))
            throw new RuntimeException("This email is already taken");

        Doctor registeredDoctor= doctorRepository.save(doctor);
        return registeredDoctor;
    }

    @Override
    public Doctor getDoctorByEmail(String email) {
       Optional <Doctor> doctor = doctorRepository.findByEmail(email);
        if(doctor.isPresent()){
            return doctor.get();
        }
        else return null;
    }

    @Override
    @Transactional
    public void deleteDoctorByEmail(String email) {
        doctorRepository.deleteByEmail(email);



    }

    @Override
    public Doctor updateDoctor(String email,Doctor newDoctor) {
        Doctor doctor= doctorRepository.findByEmail(email).get();
        if(doctor!=null){
            doctor.setEmail(newDoctor.getEmail());
            doctor.setPassword(newDoctor.getPassword());
            doctor.setFirstName(newDoctor.getFirstName());
            doctor.setLastName(newDoctor.getLastName());
            doctor.setCenter(newDoctor.getCenter());
            return doctorRepository.save(doctor);
        }
        else return null;
    }

    @Override
    public Doctor getDoctorById(UUID id) {
        Optional<Doctor> doctor = doctorRepository.findById(id);
        if(doctor.isPresent()){
            return doctor.get();
        }
        else return null;

    }

    @Override
    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findDoctorWithMinAppointments() {
        List<Doctor> doctors=doctorRepository.findAll();
        if(doctors.size()==0)
            return null;

        if(doctors.size()==1)
            return doctors.get(0);

        Map.Entry<Doctor,Integer> minEntry=null;
        Map<Doctor,Integer> docAndApp=new HashMap<>();

        for(Doctor doctor:doctors){
            List<Appointment> appointments=appointmentRepository.findAppointmentsByDoctor(doctor);
            docAndApp.put(doctor,appointments.size());
        }

        for(Map.Entry<Doctor,Integer> entry:docAndApp.entrySet()){
            if(minEntry==null || entry.getValue()<minEntry.getValue()){
                minEntry=entry;
            }
        }
        return minEntry.getKey();
    }

}
