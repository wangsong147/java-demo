package com.example.javamaildemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class OldConfig extends WebSecurityConfigurerAdapter {
    @Resource(name = "myUserDetails")
    MyUserDetailsService userDetails;

    @Autowired
    private DataSource dataSource;

    // 生成token，返回前端cookie中，存到数据库中存储对应关系
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 注入数据源
        jdbcTokenRepository.setDataSource(dataSource);
        // 自动创建表
        jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 自定义登录页面
                .loginPage("/login.html") // 登录表单页面
                .loginProcessingUrl("/user/login") // 表单数据 上传到哪个接口(security写好了这个接口，我们只需配置路径)
                .defaultSuccessUrl("/test/index").permitAll() // 登录成功跳转路径

                .and().authorizeRequests().antMatchers("login").permitAll() // 不需要认证的路径，可以直接访问
                // 设置权限, test1只有admins能访问，没有会返回403
                .antMatchers("/security/test1").hasAuthority("admins")
                // 配置多个权限
                .antMatchers("/security/test2").hasAnyAuthority("admins", "teacher", "rapper")
                // 最终源码会添加前缀 ROLE_,因此detail设置要写成ROLE_teacher
                .antMatchers("/security/test3").hasRole("teacher")
                .antMatchers("/security/test3").hasAnyRole("ROLE_")
                .anyRequest().authenticated() // 所有请求都可以访问(get,post...?)

                // 配置自动登录
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)//有效时长60s
                .userDetailsService(userDetails)

                .and().csrf().disable(); // 关闭csrf

        // 注销页面
        http.logout().logoutUrl("/security/logout").logoutSuccessUrl("/index").permitAll();
        // 自定义的没有权限页面403
        http.exceptionHandling().accessDeniedPage("/unauth.html");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
