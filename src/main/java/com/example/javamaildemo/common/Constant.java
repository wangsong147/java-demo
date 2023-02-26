package com.example.javamaildemo.common;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Constant {
    public final Integer DELETED = 1;
    public final Integer UN_DELETED = 0;
    public final LocalDateTime CREATE_TIME = LocalDateTime.now();
    public final LocalDateTime UPDATE_TIME = LocalDateTime.now();
}
