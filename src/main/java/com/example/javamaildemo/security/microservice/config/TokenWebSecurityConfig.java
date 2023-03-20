package com.example.javamaildemo.security.microservice.config;

import com.example.javamaildemo.security.microservice.filter.MicroUserDetailsService;
import com.example.javamaildemo.security.microservice.filter.TokenAuthFilter;
import com.example.javamaildemo.security.microservice.filter.TokenLoginFilter;
import com.example.javamaildemo.security.microservice.security.DefaultPasswordEncoder;
import com.example.javamaildemo.security.microservice.security.LogoutHandle;
import com.example.javamaildemo.security.microservice.security.UnauthEntryPoint;
import com.example.javamaildemo.security.microservice.util.TokenManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

import javax.annotation.Resource;

@Configuration
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource(name = "microUserDetails")
    MicroUserDetailsService userDetails;
    @Resource
    private TokenManager tokenManager;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private DefaultPasswordEncoder defaultPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilter(new WebAsyncManagerIntegrationFilter());
//        http.apply(new DefaultLoginPageConfigurer<>());

        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthEntryPoint())
                .and()
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/logout")
                .addLogoutHandler(new LogoutHandle())
                .and()
                .addFilter(new TokenLoginFilter(authenticationManager(), redisTemplate, tokenManager))
                .addFilter(new TokenAuthFilter(authenticationManager(), tokenManager, redisTemplate))
                .httpBasic();
        // 授权 + 认证
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 不需要认证的路径，可以直接访问
        web.ignoring().antMatchers("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // security底层会把传进来的密码进行加密之后跟数据库的密码进行比对，所以数据库的密码也要是加密的
        auth.userDetailsService(userDetails).passwordEncoder(defaultPasswordEncoder);
    }


}
