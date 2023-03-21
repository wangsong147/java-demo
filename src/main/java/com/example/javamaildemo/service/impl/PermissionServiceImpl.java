package com.example.javamaildemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javamaildemo.entity.Permission;
import com.example.javamaildemo.service.PermissionService;
import com.example.javamaildemo.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
* @author wangsong
* @description 针对表【permission】的数据库操作Service实现
* @createDate 2023-03-20 15:51:59
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

    @Override
    public List<String> selectById(Long id) {
        ArrayList<String> permission = new ArrayList<>();
        permission.add("p1");
        permission.add("p2");
        permission.add("p3");
        permission.add("ROLE_admin");
        return permission;
    }
}




