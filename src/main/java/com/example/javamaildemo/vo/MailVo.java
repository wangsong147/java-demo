package com.example.javamaildemo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailVo {
    private Long id;
    private String fromMail;
    private String to;
    private int age;
}
