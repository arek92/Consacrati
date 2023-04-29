package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@org.springframework.stereotype.Service
public class Service {

    private JavaMailSender sender;


    @Autowired
    public Service(JavaMailSender sender) {

        this.sender = sender;
    }

    public Service() {
    }

    public void sendSimpleEmail(String to, String subject, String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(to);
        msg.setFrom("Arkadiusz Galus <arkadiuszgalus85@gmail.com>");

        msg.setSubject(subject);
        msg.setText(content);

        sender.send(msg);
    }
}
