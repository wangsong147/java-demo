package com.example.javamaildemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javamaildemo.entity.Person;
import com.example.javamaildemo.mapper.PersonMapper;
import com.example.javamaildemo.service.PersonService;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    public void add() {
        PersonMapper personMapper = super.baseMapper;
        personMapper.insert(new Person());
    }
}
