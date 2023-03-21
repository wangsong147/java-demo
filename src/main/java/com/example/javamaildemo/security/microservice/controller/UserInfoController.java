package com.example.javamaildemo.security.microservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.javamaildemo.entity.Users;
import com.example.javamaildemo.security.microservice.util.R;
import com.example.javamaildemo.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;


import java.util.List;
import java.util.Map;

@RestController
public class UserInfoController {
    @Autowired
    private UsersService usersService;
    @Secured("ROLE_admin")
    @GetMapping("/info")
    public R info(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Map<String, Object>> maps = usersService.listMaps(new LambdaQueryWrapper<Users>().eq(Users::getUsername, username));
        return R.ok().data(maps.get(0));
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.170.181", 6379);
        String ping = jedis.ping();
        System.out.println("PING:" + ping);

    }
}
