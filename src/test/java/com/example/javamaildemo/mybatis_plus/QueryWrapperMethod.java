package com.example.javamaildemo.mybatis_plus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.javamaildemo.entity.PlusModel;
import com.example.javamaildemo.entity.RolePlayUsers;
import com.example.javamaildemo.service.RolePlayUsersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@SpringBootTest
public class QueryWrapperMethod {
    @Autowired
    private RolePlayUsersService rolePlayUsersService;

    @Test
    public void apply() {
        PlusModel plusModel = new PlusModel();
        plusModel.setUsername("ww");
        plusModel.insert();


        QueryWrapper<RolePlayUsers> wrapper = new QueryWrapper<>();
        wrapper.apply("start_time <= to_timestamp('" + getDate() + "','yyyy-MM-dd')");
//        wrapper.
        LambdaQueryWrapper<RolePlayUsers> lamRolePlayUser = wrapper.lambda();
        lamRolePlayUser
                .eq(RolePlayUsers::getStatus, 1)
                .eq(RolePlayUsers::getDeleted, 0);
        RolePlayUsers rolePlayUsers = rolePlayUsersService.getOne(wrapper);
        log.info("{}", rolePlayUsers);
    }

    private String getDate() {
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
        log.info("{}", date);
        return date;
    }
}




















