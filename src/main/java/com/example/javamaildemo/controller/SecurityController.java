package com.example.javamaildemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seccurity")
public class SecurityController {
    @GetMapping("/test1")
    public void test1() {
    }

    @GetMapping("/test2")
    public void test2() {
    }

    @GetMapping("/test3")
    public void test3() {
    }

    @GetMapping("/test4")
    public void test4() {
    }

    @GetMapping("/test5")
    public void test5() {
    }
}
