package com.example.bloodPage.service;

import com.example.bloodPage.entity.Appointment;
import com.example.bloodPage.entity.Center;
import com.example.bloodPage.entity.Doctor;
import com.example.bloodPage.entity.Donor;
import com.example.bloodPage.entity.Center;

import java.util.Date;
import java.util.List;
import java.util.UUID;


public interface AppointmentService {
    public void saveAppointment(Appointment appointment);
    public List<Appointment> findUserAppointments(Donor donor);
    public void deleteUserAppointments(Donor donor);
    public void deleteAppointmentById(UUID id);
    public Appointment findAppointmentById(UUID id);
    public List<Appointment> findAllDoctorAppointments(Doctor doctor);
    List<Appointment> findAllAppointments();
    List<Appointment> findAllDoctorAppointmentToday(Doctor doctor);
}
