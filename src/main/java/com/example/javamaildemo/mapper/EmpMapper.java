package com.example.javamaildemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javamaildemo.entity.Dep;
import com.example.javamaildemo.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {
    void association();
}
