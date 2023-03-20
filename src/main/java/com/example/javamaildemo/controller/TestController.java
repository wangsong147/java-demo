package com.example.javamaildemo.controller;

import com.example.javamaildemo.security.microservice.util.R;
import com.example.javamaildemo.security.microservice.util.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class TestController {
    @GetMapping("test199")
    public String testtt(HttpServletRequest request, HttpServletResponse response) {
        String authorization = request.getHeader("Authorization");
        log.info(authorization);
//        response.setStatus(500);
        response.setStatus(200);
        log.info("test_ok");
        return "test_ok";
    }

    @GetMapping("/response")
    public void res(HttpServletRequest request, HttpServletResponse response) {
        ResponseUtil.out(response, R.ok());
    }
}
