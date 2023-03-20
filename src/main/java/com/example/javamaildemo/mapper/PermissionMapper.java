package com.example.javamaildemo.mapper;

import com.example.javamaildemo.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author wangsong
* @description 针对表【permission】的数据库操作Mapper
* @createDate 2023-03-20 15:51:59
* @Entity com.example.javamaildemo.entity.Permission
*/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}




