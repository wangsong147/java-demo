package com.example.javamaildemo.entity;

import lombok.Data;

@Data
public class Emp {
    private Long empId;
    private String empName;
    private Integer age;
    private String email;
    // 多对一：多个员工对应一个部门
    private Dep dep;
}
