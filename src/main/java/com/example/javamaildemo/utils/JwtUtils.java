package com.example.javamaildemo.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import io.jsonwebtoken.Jwts;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static String KEY = "aabb";

    public static String create(String userName, String password) {
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, 10);

        Map<String,Object> payload = new HashMap<>();
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷
        payload.put("userName", "zhangsan");
        payload.put("passWord", "666889");


        String token = JWTUtil.createToken(payload, KEY.getBytes());
        return token;
    }

    public static void parse(String token) {
        //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJwYXNzV29yZCI6IjY2Njg4OSIsIm5iZiI6MTYzNTE1MDI3NiwiZXhwIjoxNjM1MTUwODc2LCJ1c2VyTmFtZSI6InpoYW5nc2FuIiwiaWF0IjoxNjM1MTUwMjc2fQ.Cq2AHyrZ-Q7U7O5BBPdEIBrm7aDtjQh4ZDvtIcLzQvg";
        JWT jwt = JWTUtil.parseToken(token);

        boolean verifyKey = jwt.setKey(KEY.getBytes()).verify();
        System.out.println(verifyKey);

        boolean verifyTime = jwt.validate(0);
        System.out.println(verifyTime);
    }
}
