package com.example.javamaildemo.security.microservice.security;

import com.example.javamaildemo.security.microservice.util.R;
import com.example.javamaildemo.security.microservice.util.ResponseUtil;
import com.example.javamaildemo.security.microservice.util.TokenManager;
import com.example.javamaildemo.utils.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogoutHandle implements LogoutHandler {
    private RedisTemplate redisTemplate;

    public LogoutHandle(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // logout也是一个请求
        String token = request.getHeader("token");
        if (token != null) {
            // 前端把localstorage中的token也删除掉
            // 删除token: k->token,v->权限
            String username = TokenManager.getUserInfoFromToken(token);
            log.info("{}",redisTemplate);
            redisTemplate.delete(username);
        }
        ResponseUtil.out(response, R.ok());

    }
}
