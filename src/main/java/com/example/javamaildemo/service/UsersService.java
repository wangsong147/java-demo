package com.example.javamaildemo.service;

import com.example.javamaildemo.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author wangsong
* @description 针对表【users】的数据库操作Service
* @createDate 2023-03-17 09:48:17
*/
public interface UsersService extends IService<Users> {

    Map<String, Object> getUserInfo(String usersService);
}
