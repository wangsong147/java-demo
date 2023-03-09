package com.example.javamaildemo.utils;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    private static String KEY = "my_key";

    public static String create(String userName) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime newTime = now.plusMinutes(10);

        Map<String,Object> payload = new HashMap<>();
        //签发时间：当前时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间: 10分钟后
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间：当前时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷：用户信息
        payload.put("userName", userName);
        // hu-tool默认使用hs256算法加密
        return JWTUtil.createToken(payload, KEY.getBytes());
    }

}
