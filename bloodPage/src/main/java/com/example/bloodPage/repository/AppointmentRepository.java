package com.example.bloodPage.repository;

import com.example.bloodPage.entity.Appointment;
import com.example.bloodPage.entity.Doctor;
import com.example.bloodPage.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    List<Appointment> findAll();

    void deleteById(UUID id);
    Appointment findAppointmentById( UUID id);

    @Query("SELECT a FROM Appointment a WHERE a.donor = :donor")
    List<Appointment> findUserAppointments(@Param("donor") Donor donor);
    @Query("SELECT a FROM Appointment a WHERE a.doctor = :doctor")
    List<Appointment> findAppointmentsByDoctor(@Param("doctor") Doctor doctor);

   @Query("SELECT a FROM Appointment a WHERE  a.date = :now")
    List<Appointment> findByDate(LocalDate now);
}
