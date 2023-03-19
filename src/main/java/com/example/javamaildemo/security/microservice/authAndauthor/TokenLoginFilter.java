package com.example.javamaildemo.security.microservice.authAndauthor;

import com.example.javamaildemo.entity.SecurityUser;
import com.example.javamaildemo.entity.Users;
import com.example.javamaildemo.security.microservice.util.R;
import com.example.javamaildemo.security.microservice.util.ResponseUtil;
import com.example.javamaildemo.security.microservice.util.TokenManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private AuthenticationManager authenticationManager;

    // 这个方法是为了重写successful方法利用设置token才重写的（基于web的验证默认走的是security的username..filter）
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 获取表单数据，优雅的封装
            Users users = new ObjectMapper().readValue(request.getInputStream(), Users.class);
            // 会调用userdetailservice查数据库校验
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getUsername(), users.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException();
        }
    }

    /**
     * 身份验证成功
     * attemptAuthentication方法返回值传给这个
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 认证成功，得到认证成功之后用户信息
        SecurityUser user = (SecurityUser) authResult.getPrincipal();
        String username = user.getCurrentUserInfo().getUsername();
        List<String> authorities = user.getPermissionValueList();
        // 生成token
        String token = TokenManager.createToken(username);
        // 存redis
        redisTemplate.opsForValue().set(username, authorities);
        //返回token
        ResponseUtil.out(response, R.ok().data("token", token));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, R.error());
    }
}
