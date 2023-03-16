package com.example.javamaildemo.security;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.annotation.Resource;

@Configuration
public class OldConfig extends WebSecurityConfigurerAdapter {
    @Resource(name = "myUserDetails")
    MyUserDetailsService userDetails;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 自定义的没有权限页面403
        http.exceptionHandling().accessDeniedPage("/unauth.html");
        http.formLogin() // 自定义登录页面
                .loginPage("/login.html") // 登录表单页面
                .loginProcessingUrl("/user/login") // 表单数据 上传到哪个接口(security写好了这个接口，我们只需配置路径)
                .defaultSuccessUrl("/test/index").permitAll() // 登录成功跳转路径
                .and().authorizeRequests().antMatchers( "login").permitAll() // 不需要认证的路径，可以直接访问
                // 设置权限, test1只有admins能访问，没有会返回403
                .antMatchers("/security/test1").hasAuthority("admins")
                // 配置多个权限
                .antMatchers("/security/test2").hasAnyAuthority("admins","teacher","rapper")
                // 最终源码会添加前缀 ROLE_,因此detail设置要写成ROLE_teacher
                .antMatchers("/security/test3").hasRole("teacher")
                .antMatchers("/security/test3").hasAnyRole("ROLE_")
                .anyRequest().authenticated() // 所有请求都可以访问(get,post...?)
                .and().csrf().disable(); // 关闭csrf
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // =================================================== 替换废弃的adapter ====================================================================================

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetails)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
        return authenticationManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.formLogin()
                //默认登录页面
                .loginPage("/login.html")
                //登录的处理路径，无需自己创建该路径的业务处理功能。
                .loginProcessingUrl("/login")
                //通过这两个可以修改表单中name的值
                /*.usernameParameter("user")
                .passwordParameter("pwd")*/

                //放行
                .permitAll()
                //其他的请求都需要认证
                .and()
                .authorizeRequests().anyRequest().authenticated()

                //禁用跨域请求伪造 csrf
                .and()
                .csrf().disable()
                .build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }


}
