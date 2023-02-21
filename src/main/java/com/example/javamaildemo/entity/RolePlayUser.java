package com.example.javamaildemo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RolePlayUser {

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
