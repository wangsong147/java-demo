package com.example.javamaildemo.service.impl;

import com.example.javamaildemo.entity.Person;
import com.example.javamaildemo.service.LoginService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Override
    public Person getUserInfo(String userName, String password) {
        return new Person(999L,"aa",33,"123");
    }

    @Override
    @Async
    public String async() throws InterruptedException {
        Thread.sleep(3000L);
        System.out.println("3秒后：输出async");
        return "3秒后：输出async";
    }
}
