package com.example.javamaildemo.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.javamaildemo.JavaMailDemoApplication;
import com.example.javamaildemo.entity.Users;
import com.example.javamaildemo.mapper.UsersMapper;
import com.example.javamaildemo.service.UsersService;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = JavaMailDemoApplication.class)
public class JdbcTest {
    @Resource
    private JdbcOperations jdbcOperations;

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private UsersService usersService;

    @Resource
    private UsersMapper usersMapper;

    @Test
    public void selectMaps(){
//        usersService.listMaps().forEach(System.out::println);
//        usersService.list().forEach(System.out::println);
        System.out.println(RequestContextHolder.getRequestAttributes());

    }

    @Test
    public void query() {
        Integer i = jdbcOperations.queryForObject("select count(*) from t_users", Integer.class);
        System.out.println(i);
        List<Users> usersList = jdbcOperations.query("select * from t_users", new BeanPropertyRowMapper<>(Users.class));
        System.out.println(usersList);
        List<Users> query = jdbcOperations.query("select * from t_users", (rs, rowNum) -> {
            Users users = new Users();
            users.setUsername(rs.getString("username"));
            users.setId(rs.getLong("id"));
            users.setPassword(rs.getString("password"));
            return users;
        });
        System.out.println(query);

        List<Users> lll = usersService.list(new LambdaQueryWrapper<Users>().eq(Users::getUsername, "lll"));
        System.out.println(lll);

        Users users = usersMapper.selectOne(new LambdaQueryWrapper<Users>().eq(Users::getUsername, "lll"));
        System.out.println(users);

    }
}
