//package com.example.javamaildemo.controller;
//
//import com.example.javamaildemo.vo.MailVo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Slf4j
//@RestController
//public class TestController {
//    @GetMapping("test199")
//    public String testtt(HttpServletRequest request, HttpServletResponse response) {
//        String authorization = request.getHeader("Authorization");
//        log.info(authorization);
////        response.setStatus(500);
//        response.setStatus(200);
//        log.info("test_ok");
//        return "test_ok";
//    }
//}
