package com.example.javamaildemo.service;

import com.example.javamaildemo.entity.Person;

public interface LoginService {
    Person getUserInfo(String userName, String password);
}
