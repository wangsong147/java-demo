package com.example.javamaildemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class XssFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 用xss wrapper包装一下request，把所有参数header都转义，后面controller接收到的request其实就是XssRequest了
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(request);// 这里相当于把request的信息传递给Xss了
        filterChain.doFilter(xssRequest,response);
    }
}
