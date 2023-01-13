package com.example.javamaildemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class JavaMailDemoApplicationTests {
    @Autowired
    JavaMailSender mailSender;

    @Test
    void testSendEmail() {
        SimpleMailMessage simpMsg = new SimpleMailMessage();
        /*
         * 553 Mail from must equal authorized user
         * 发件人必须和授权用户一致
         */
         simpMsg.setFrom("song5_wang@163.com");
//        simpMsg.setFrom("ws18740278017@163.com");
        simpMsg.setTo("18945588017@163.com");
        simpMsg.setSubject("subject");
        simpMsg.setText("content");
        mailSender.send(simpMsg);
        String conflicts = "conflicts".toUpperCase();
    }

}
