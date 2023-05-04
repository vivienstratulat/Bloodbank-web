package com.example.bloodPage.email;

import com.example.bloodPage.entity.Appointment;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


@Service
public class EmailService  {
    @Autowired
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String firstName, String lastName, LocalDate date, String centerName, String doctorLastName, String doctorFirstName) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("cevamail@gmail.com");
        message.setTo(to);
        StringBuilder body=new StringBuilder();
        body.append("Dear ").append(firstName).append(" ").append(lastName).append(",\n");
        body.append("Your appointment is confirmed for ").append(date).append(" at ").append(centerName).append(" with doctor ").append(doctorLastName).append(" ").append(doctorFirstName).append(".\n");
        message.setText(body.toString());
        message.setSubject("APPOINTMENT CONFIRMATION");
        mailSender.send(message);


        System.out.println("Mail sent!!!!!!!!!!!!!!!!!!!");
    }

    public void  sendReminder(Appointment appointment) {

        String to=appointment.getDonor().getEmail();
        String firstName=appointment.getDonor().getFirstName();
        String lastName=appointment.getDonor().getLastName();
        LocalDate date=appointment.getDatetime();
        String centerName=appointment.getCenter().getName();
        String doctorLastName=appointment.getDoctor().getLastName();
        String doctorFirstName=appointment.getDoctor().getFirstName();


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("cevamail@gmail.com");
        message.setTo(to);
        StringBuilder body = new StringBuilder();
        body.append("Dear ").append(firstName).append(" ").append(lastName).append(",\n");
        body.append("Dont forget your appointment on ").append(date).append(" at ").append(centerName).append(" with doctor ").append(doctorLastName).append(" ").append(doctorFirstName).append(".\n");
        message.setText(body.toString());
        message.setSubject("REMINDER APPOINTMENT");
        mailSender.send(message);
        System.out.println("Reminder????????");

    }
}