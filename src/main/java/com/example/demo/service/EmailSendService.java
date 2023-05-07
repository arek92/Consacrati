package com.example.demo.service;


import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

@org.springframework.stereotype.Service
public class EmailSendService {


    @Autowired
    private static JavaMailSender sender;

    @Autowired
    private static UserRepository repository;


    public EmailSendService() {
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

