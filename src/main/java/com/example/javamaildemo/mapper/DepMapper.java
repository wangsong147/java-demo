package com.example.javamaildemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javamaildemo.entity.Dep;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepMapper extends BaseMapper<Dep> {
    void collection();
}
