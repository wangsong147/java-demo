//package com.example.javamaildemo.security.microservice.authAndauthor;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//public class TokenAuthFilter extends BasicAuthenticationFilter {
//
//    public TokenAuthFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    public TokenAuthFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
//        super(authenticationManager, authenticationEntryPoint);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        // 获取到那个认证成功的用户信息
//        String token = request.getHeader("token");
//        getAuthorizes()
//        chain.doFilter(request, response);
//    }
//
//    private void getAuthorizes() {
//    }
//}
