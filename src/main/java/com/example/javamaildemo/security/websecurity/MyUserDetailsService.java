//package com.example.javamaildemo.security.websecurity;
//
//import cn.hutool.crypto.digest.MD5;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.example.javamaildemo.security.microservice.entity.Users;
//import com.example.javamaildemo.mapper.UsersMapper;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
//@Service("myUserDetails")
//public class MyUserDetailsService implements UserDetailsService {
//    @Resource
//    private UsersMapper usersMapper;
//
//    /**
//     * 对前端传入的username进行查询，并返回用户名和密码，供config校验
//     *
//     * @param username
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        LambdaQueryWrapper<Users> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Users::getUsername, username);
//        Users users = usersMapper.selectOne(wrapper);
//        if (users == null) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        // 微服务 - 权限要解析token去redis里查
//        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_teacher");
//        return new User(users.getUsername(), passwordEncoder.encode(users.getPassword()), authorities);
//    }
//
//}