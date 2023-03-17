package com.example.javamaildemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController {
    @Secured({"ROLE_teacher", "ROLE_student"})
    @GetMapping("/test1")
    public void test1() {
    }

    @PreAuthorize("hasAnyAuthority('admins','student')")
    @GetMapping("/test2")
    public void test2() {
    }

    @PostAuthorize("hasAnyAuthority('rapper')")
    @GetMapping("/test3")
    public void test3() {
    }

    @PostMapping("/login")
    public void test4() {
    }

    @GetMapping("/logout")
    public void test5() {
    }
}
