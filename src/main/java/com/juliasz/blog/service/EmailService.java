package com.juliasz.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendConfirmationEmail(String recipientEmail, String token) {
        String subject = "Please confirm your email";
        String confirmationUrl = "http://localhost:8090/user/validate/" + token;
        String message = "Thank you for registering. Please click the link below to activate your account:\n"
                + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientEmail);
        email.setSubject(subject);
        email.setText(message);

        mailSender.send(email);
    }

}
