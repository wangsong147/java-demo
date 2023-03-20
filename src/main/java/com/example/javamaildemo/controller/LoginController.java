package com.example.javamaildemo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javamaildemo.entity.Person;
import com.example.javamaildemo.entity.UserInfo;
import com.example.javamaildemo.service.LoginService;
import com.example.javamaildemo.service.UsersService;
import com.example.javamaildemo.utils.JwtUtils;
import com.example.javamaildemo.utils.ResultMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private UsersService usersService;

//    @GetMapping("/login")
//    public ResultMessage<String> login() {
//        return ResultMessage.ok("ok_");
//    }
//
//    @PostMapping("/login")
//    public ResultMessage<String> loginn(UserInfo userInfo, @RequestHeader("Authorization") String headerToken) {
//        log.info(headerToken);
//        String userName = userInfo.getUserName();
//        String password = userInfo.getPassword();
//        // 1.身份认证通过 2.创建token
//        Person user = loginService.getUserInfo(userName, password);
//        if (user != null) {
//            String token = JwtUtils.create(userName);
//            return ResultMessage.ok(token);
//        }
//        return ResultMessage.error(500, "用户不存在");
//    }

    @GetMapping("/page")
    public ResultMessage<IPage> page() {
        return ResultMessage.ok(usersService.getBaseMapper().selectPage(new Page<>(1, 10), null));
    }

    @GetMapping("/async")
    public String async() throws InterruptedException {
        String async = loginService.async();
        System.out.println("sout: ok");
        return "ok";
    }

    @GetMapping("/logout")
    public void logout() {
        log.info("logout_ok");
    }

    @GetMapping("/unauth")
    public String unauth() {
        log.info("unauth");
        return "access_denied: 403_unauth";
    }

}
