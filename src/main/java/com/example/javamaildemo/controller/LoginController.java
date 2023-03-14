package com.example.javamaildemo.controller;

import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javamaildemo.entity.Person;
import com.example.javamaildemo.entity.UserInfo;
import com.example.javamaildemo.service.LoginService;
import com.example.javamaildemo.service.PersonService;
import com.example.javamaildemo.utils.JwtUtils;
import com.example.javamaildemo.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @Autowired
    PersonService personService;

    @PostMapping("/login")
    public R<String> login(UserInfo userInfo, @RequestHeader("Authorization") String headerToken) {
        log.info(headerToken);
        String userName = userInfo.getUserName();
        String password = userInfo.getPassword();
        // 1.身份认证通过 2.创建token
        Person user = loginService.getUserInfo(userName, password);
        if (user != null) {
            String token = JwtUtils.create(userName);
            return R.ok(token);
        }
        return R.error(500, "用户不存在");
    }

    @GetMapping("/page")
    public R<IPage> page() {
        IPage<Person> page = personService.getBaseMapper().selectPage(new Page<>(1, 10), null);
        return R.ok(page);
    }

    @GetMapping("/async")
    public String async() throws InterruptedException {
        String async = loginService.async();
        System.out.println("sout: ok");
        return "ok";
    }
}
