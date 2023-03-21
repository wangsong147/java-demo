package com.example.javamaildemo.security.microservice.util;

import cn.hutool.crypto.digest.MD5;
import lombok.Data;

@Data
public class Md5 {
    public static String encode(CharSequence password) {
        return MD5.create().digestHex(password.toString());
    }

    public static void main(String[] args) {
        CharSequence password = "admin";
        System.out.println(MD5.create().digestHex(password.toString()));
    }
}
