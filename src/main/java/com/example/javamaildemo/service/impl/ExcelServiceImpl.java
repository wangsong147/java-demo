package com.example.javamaildemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javamaildemo.entity.Person;
import com.example.javamaildemo.mapper.ExcelMapper;
import com.example.javamaildemo.service.ExcelService;
import org.springframework.stereotype.Service;

@Service
public class ExcelServiceImpl extends ServiceImpl<ExcelMapper, Person> implements ExcelService {
}
