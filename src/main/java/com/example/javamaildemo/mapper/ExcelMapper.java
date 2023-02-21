package com.example.javamaildemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javamaildemo.entity.Person;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExcelMapper extends BaseMapper<Person> {
}
