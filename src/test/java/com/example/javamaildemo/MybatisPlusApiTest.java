package com.example.javamaildemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.javamaildemo.entity.Person;
import com.example.javamaildemo.mapper.PersonMapper;
import com.example.javamaildemo.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
class MybatisPlusApiTest {
    @Autowired
    PersonMapper personMapper;

    @Autowired
    PersonService personService;

    @Test
    void pageTest(){
        Page<Person> personPage = new Page<>();
        personPage.setCurrent(2).setSize(2);
        IPage<Person> page = personMapper.selectPage(personPage, null);
        System.out.println(page.getTotal());
    }
    @Test
    void wapperTest(){
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                // 指定查询列
                .select(Person::getId,Person::getName)
                // 优先级：SELECT * FROM t_person WHERE id=1 AND (name='zs' or age>18)
                .and(w -> w.eq(Person::getName, "zs").or().in(Person::getId, 1L, 2L, 3L, 4L))
                .or()
                .isNull(Person::getId)
                // 子查询
                .inSql(Person::getId,"SELECT * FROM t_person WHERE id>18");
    }

    @Test
    void baseMapperTest() {
        HashMap<String, Object> map = new HashMap<>();
        // map作为条件传参
        int i = personMapper.deleteByMap(map);
        List<Person> personList = personMapper.selectByMap(map);
        // 每一行结果，装进一个map集合，而不是对象(适用于一次性的数据，没有实体类的)
        List<Map<String, Object>> maps = personMapper.selectMaps(new QueryWrapper<Person>().isNull("name"));

    }

    @Test
    void serviceTest() {
        // queryWrapper 子查询，优先级
        personService.update(new LambdaUpdateWrapper<Person>()
                .set(Person::getName, "w")
                .or()
                .isNull(Person::getId)
                // 子查询
                .inSql(Person::getId,"SELECT * FROM t_person WHERE id>18")
        );
    }

    @Test
    void keyGenerateTest(){
        // 测试主键
        Person person = new Person();
        person.setId(null);
        person.setAge(111);
        person.setName("person");
        personService.save(person);
    }


}
