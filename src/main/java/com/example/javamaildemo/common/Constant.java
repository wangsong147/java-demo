package com.example.javamaildemo.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Constant {
    public final static Integer DELETED = 1;
    public final static Integer UN_DELETED = 0;
    public final static LocalDateTime CREATE_TIME = LocalDateTime.now();
    public final static LocalDateTime UPDATE_TIME = LocalDateTime.now();
    public final static String JWT_KEY = "myKey";
}
