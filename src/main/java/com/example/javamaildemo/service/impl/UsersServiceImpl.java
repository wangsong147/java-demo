package com.example.javamaildemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javamaildemo.entity.Users;
import com.example.javamaildemo.service.UsersService;
import com.example.javamaildemo.mapper.UsersMapper;
import org.springframework.stereotype.Service;

/**
* @author wangsong
* @description 针对表【users】的数据库操作Service实现
* @createDate 2023-03-17 09:48:17
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users>
    implements UsersService{

}




