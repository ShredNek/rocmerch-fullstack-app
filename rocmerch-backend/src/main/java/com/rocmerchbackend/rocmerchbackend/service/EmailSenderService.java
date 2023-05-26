package com.rocmerchbackend.rocmerchbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail,
                          String subject,
                          String body) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("danielleemusic98@gmail.com");
        message.setTo("danielleemusic98@gmail.com");
        message.setText(body);
        message.setSubject(subject);

//        try {
        mailSender.send(message);
        System.out.println("Mail sent!");
//        } catch(RuntimeException error) {
//            System.out.println(error);
//        }

    }

}
