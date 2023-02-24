package com.example.javamaildemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolePlayUser {

    private LocalDateTime startTime;

    private LocalDateTime endTime;
}
