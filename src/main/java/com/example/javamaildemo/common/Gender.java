package com.example.javamaildemo.common;

public enum Gender {
    male(0, "男"),
    female(1, "女");
    private final Integer i;
    private final String gender;
    Gender(Integer i, String gender) {
        this.i = i;
        this.gender = gender;
    }
}
