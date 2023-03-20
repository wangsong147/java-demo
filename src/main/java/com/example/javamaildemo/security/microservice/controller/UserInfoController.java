package com.example.javamaildemo.security.microservice.controller;

import com.example.javamaildemo.security.microservice.util.R;
import com.example.javamaildemo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserInfoController {
    @Autowired
    private UsersService usersService;
    @GetMapping("/info")
    public R info(){
        String username = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String,Object> userInfo = usersService.getUserInfo(username);
        return R.ok().data(userInfo);
    }
}
