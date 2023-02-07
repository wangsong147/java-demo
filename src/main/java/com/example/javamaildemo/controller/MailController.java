package com.example.javamaildemo.controller;

import com.example.javamaildemo.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Session;

@RestController
@RequestMapping("mail")
public class MailController {
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("send")
    public void send(MailVo mailVo) {



        SimpleMailMessage simpMsg = new SimpleMailMessage();
        /*
         * 553 Mail from must equal authorized user
         * 发件人必须和授权用户一致
         */
//         simpMsg.setFrom("song5.wang@yidatec.com");
        simpMsg.setFrom("song5_wang@163.com");
//        simpMsg.setFrom("ws18740278017@163.com");
        simpMsg.setTo(mailVo.getTo());
        simpMsg.setSubject("subject");
        simpMsg.setText("content");
        mailSender.send(simpMsg);
    }


}
