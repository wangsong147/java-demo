package com.example.javamaildemo.utils;

import cn.hutool.cache.Cache;
import cn.hutool.cache.CacheUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.example.javamaildemo.common.Constant;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtUtils {

    public static String create(String userName) {
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.SECOND, 30);
        log.info("开始时间:{}-过期时间:{}", now, newTime);

        Map<String, Object> payload = new HashMap<>();
        //签发时间：当前时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间: 10分钟后
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间：当前时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷：用户信息
        payload.put("userName", userName);
        // hu-tool默认使用hs256算法加密
        return JWTUtil.createToken(payload, Constant.JWT_KEY.getBytes());
    }


    public static void main(String[] args) {
        Cache<String, String> objects = CacheUtil.newFIFOCache(3);
        String token = create(Constant.JWT_KEY);
        System.out.println(token);
        String checkToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE2NzgzNDczMzgsImV4cCI6MTY3ODM0NzM2OCwidXNlck5hbWUiOiJteUtleSIsImlhdCI6MTY3ODM0NzMzOH0.Vazc_Uu-98bWG9otUeBJvv7NvDe9eFrO7n3IupwiCZc";

        JWT jwt = JWTUtil.parseToken(checkToken);
        boolean validate = jwt.setKey(Constant.JWT_KEY.getBytes()).validate(0);
        boolean validate2 = jwt.validate(0);

        System.out.println(validate);
        System.out.println(validate2);

        // 只验证合法性，不验证过不过期
        System.out.println(JWTUtil.verify(checkToken, Constant.JWT_KEY.getBytes()));
    }
}
