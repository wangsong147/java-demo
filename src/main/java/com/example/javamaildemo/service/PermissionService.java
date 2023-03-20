package com.example.javamaildemo.service;

import com.example.javamaildemo.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author wangsong
* @description 针对表【permission】的数据库操作Service
* @createDate 2023-03-20 15:51:59
*/
public interface PermissionService extends IService<Permission> {

    List<String> selectById(Long id);
}
