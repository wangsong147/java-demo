package com.example.javamaildemo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Dep {
    private Long depId;
    private String depName;
    // 一对多,多表查询时：dep left join emp
    private List<Emp> emps;
}
