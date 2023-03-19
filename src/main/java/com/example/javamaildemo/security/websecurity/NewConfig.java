//package com.example.javamaildemo.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.annotation.Resource;
//
//@Configuration
//public class NewConfig extends WebSecurityConfigurerAdapter {
//    @Resource(name = "myUserDetails")
//    MyUserDetailsService userDetails;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    // =================================================== 替换废弃的adapter ====================================================================================
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetails)
//                .passwordEncoder(passwordEncoder())
//                .and()
//                .build();
//        return authenticationManager;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.formLogin()
//                //默认登录页面
//                .loginPage("/login.html")
//                //登录的处理路径，无需自己创建该路径的业务处理功能。
//                .loginProcessingUrl("/login")
//                //通过这两个可以修改表单中name的值
//                /*.usernameParameter("user")
//                .passwordParameter("pwd")*/
//
//                //放行
//                .permitAll()
//                //其他的请求都需要认证
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
//
//                //禁用跨域请求伪造 csrf
//                .and()
//                .csrf().disable()
//                .build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
//    }
//
//
//}
