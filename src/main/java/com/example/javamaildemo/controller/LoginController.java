package com.example.javamaildemo.controller;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.example.javamaildemo.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class LoginController {
    @PostMapping("/token")
    public void login(UserInfo userInfo, @RequestHeader("Authorization") String token) {
        // 1.身份认证 通过
        // 2.无token -> createToken
        // 3.有token -> checkToken (可以放在拦截器做check)
        JWT jwt = JWTUtil.parseToken(token);
        log.info(jwt.toString());
    }
}
