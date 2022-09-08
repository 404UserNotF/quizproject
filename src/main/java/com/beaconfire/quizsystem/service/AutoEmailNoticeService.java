package com.beaconfire.quizsystem.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Log4j2
@Service
public class AutoEmailNoticeService {
    /*@Autowired
    private JavaMailSender emailSender;
    @Value("${spring.mail.username}")
    private String from;
    *//**
     * Send an email every 30 sec
     * *//*
    @Scheduled(cron = "0/30 * * * * *")
    public void autoSendEmail(){
        try{
            Long now = System.currentTimeMillis();
            log.warn("Sending a email" + new SimpleDateFormat().format(now));
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo("jksonlin3@gmail.com");
            message.setSubject("Test");
            message.setText("This is a test message");
            emailSender.send(message);
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/
}
