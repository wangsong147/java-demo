package com.example.javamaildemo.security.microservice.filter;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.javamaildemo.entity.SecurityUser;
import com.example.javamaildemo.entity.Users;
import com.example.javamaildemo.mapper.UsersMapper;
import com.example.javamaildemo.service.PermissionService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("microUserDetails")
public class MicroUserDetailsService implements UserDetailsService {
    @Resource
    private UsersMapper usersMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private PermissionService permissionService;


    /**
     * 对前端传入的username进行查询，并返回用户名和密码，供config校验
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Users::getUsername, username);
        Users users = usersMapper.selectOne(wrapper);
        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        // 查找权限
        List<String> permissions = permissionService.selectById(users.getId());

        SecurityUser securityUser = new SecurityUser();
        securityUser.setCurrentUserInfo(users);
        securityUser.setPermissionValueList(permissions);
        return securityUser;
    }

}