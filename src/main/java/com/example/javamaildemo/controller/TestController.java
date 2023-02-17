package com.example.javamaildemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RequestMapping("test")
@RestController
public class TestController {
    @GetMapping("test")
    public void test(HttpServletRequest request) {
        System.out.println("test");
    }
}
