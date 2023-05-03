package com.example.bloodPage.email;

import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;


@Service
public class EmailService  {
    @Autowired
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String firstName, String lastName, Date date, String centerName, String doctorLastName, String doctorFirstName) {
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


}