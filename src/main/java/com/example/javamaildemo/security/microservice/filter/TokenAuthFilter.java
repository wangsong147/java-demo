package com.example.javamaildemo.security.microservice.filter;

import com.example.javamaildemo.security.microservice.util.TokenManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * redis获取权限信息
 * new UsernamePasswordAuthenticationToken(username, token, authorities);
 * set到上下文中
 */
public class TokenAuthFilter extends BasicAuthenticationFilter {
    private TokenManager tokenManager;
    private RedisTemplate redisTemplate;

    public TokenAuthFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate redisTemplate) {
        super(authenticationManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取当前认证成功用户权限信息
        UsernamePasswordAuthenticationToken authRequest = getAuthorities(request);
        // 把权限信息放到上下文中
        if (authRequest != null) {
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthorities(HttpServletRequest request) {
        String token = request.getHeader("token");
        if (token != null) {
            String username = tokenManager.getUserInfo(token);
            // 相当于解析了token，token被篡改了就get权限，也就无法访问接口功能
            List<String> permissionValueList = (List<String>) redisTemplate.opsForValue().get(username);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String permissionValue : permissionValueList) {
                // 转换
                SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(permissionValue);
                authorities.add(simpleGrantedAuthority);
            }
            return new UsernamePasswordAuthenticationToken(username, token, authorities);
        }
        return null;
    }


}
