package com.example.javamaildemo.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.javamaildemo.entity.Person;
import com.example.javamaildemo.mapper.PersonMapper;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("myUserDetails")
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private PersonMapper personMapper;

    /**
     * 对前端传入的username进行查询，并返回用户名和密码，供config校验
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<Person> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Person::getName, username);
        Person person = personMapper.selectOne(wrapper);
        if (person == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("student,rapper");
        return new User(person.getName(), MD5Encoder.encode(person.getPassword().getBytes()), authorities);
    }

}