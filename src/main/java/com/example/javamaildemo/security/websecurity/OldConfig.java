//package com.example.javamaildemo.security.websecurity;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
//import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
//
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//
//@Configuration
//public class OldConfig extends WebSecurityConfigurerAdapter {
//    @Resource(name = "myUserDetails")
//    MyUserDetailsService userDetails;
//
//    @Resource
//    private DataSource dataSource;
//
//    // 生成token，返回前端cookie中，存到数据库中存储对应关系
//    @Bean
//    public PersistentTokenRepository persistentTokenRepository(){
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        // 注入数据源
//        jdbcTokenRepository.setDataSource(dataSource);
//        // 自动创建表
////        jdbcTokenRepository.setCreateTableOnStartup(true);
//        return jdbcTokenRepository;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // security底层会把传进来的密码进行加密之后跟数据库的密码进行比对，所以数据库的密码也要是加密的
////        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////        String admin = passwordEncoder.encode("admin");
////        auth.inMemoryAuthentication().withUser("admin").password(admin).roles("admin");
//        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // anyRequest 表示所有请求都需要认证-也可以指定只有get请求需要认证:requests.antMatchers(HttpMethod.GET).
////        http.authorizeRequests(requests -> requests.anyRequest().authenticated());
////        http.httpBasic();
////        http.formLogin(); // 不写loginPage则使用默认的
//        http.formLogin()
////                .loginPage("/login.html") // 自定义登录页面:登录表单页面
////                .loginProcessingUrl("/user/login") // 表单数据 上传到哪个接口controller(security已经写了这个接口，我们只需配置路径)
////                .defaultSuccessUrl("/test/index")
////                .permitAll()
//
//                .and()
//                .authorizeRequests()
//                // 不需要认证的路径，可以直接访问
//                    .antMatchers("/login","/security/login")
//                    .permitAll()
//
//                // 设置接口的单个权限,多个权限
//                    .antMatchers("/security/test1").hasAuthority("admins")
//                    .antMatchers("/security/test2").hasAnyAuthority("admins", "teacher", "rapper")
//                // 最终源码会添加前缀 ROLE_,因此detail设置要写成ROLE_teacher
//                    .antMatchers("/security/test3").hasRole("teacher")
//                    .antMatchers("/security/test3").hasAnyRole("ROLE_")
//                    .anyRequest().authenticated() // 所有请求方法都可以访问(get,post...)
//
//                // 配置自动登录
//                .and().rememberMe().tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(60)//有效时长60s
//                .userDetailsService(userDetails)
//
//                .and().csrf().disable(); // 关闭csrf: 注掉就可以开启了，底层是CsrfFilter实现的(通过token)
////
//        // 注销页面
//        http.logout().logoutUrl("/logout").logoutSuccessUrl("/security/logout").permitAll();
//        // 自定义的没有权限页面403
//        http.exceptionHandling().accessDeniedPage("/unauth");
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//    }
//
//
//    /**
//     * 密码编码器：按照这个加密方法加密
//     * 必须要加的bean，加密的时候要用这个接口：PasswordEncoder
//     * 接口里有matches方法判断编码后的密码是否相同
//     * rawPassword
//     *
//     * @return {@link PasswordEncoder}
//     */
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//}
