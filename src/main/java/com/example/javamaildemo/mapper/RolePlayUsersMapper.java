package com.example.javamaildemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javamaildemo.entity.RolePlayUsers;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangsong
 * @description 针对表【role_play_users】的数据库操作Mapper
 * @createDate 2023-03-23 09:39:28
 * @Entity com.example.javamaildemo.entity.RolePlayUsers
 */
@Mapper
public interface RolePlayUsersMapper extends BaseMapper<RolePlayUsers> {

}




