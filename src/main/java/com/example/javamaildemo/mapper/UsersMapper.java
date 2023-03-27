package com.example.javamaildemo.mapper;

import com.example.javamaildemo.security.microservice.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wangsong
* @description 针对表【users】的数据库操作Mapper
* @createDate 2023-03-17 09:48:17
* @Entity com.example.javamaildemo.security.microservice.entity.Users
*/
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

}




