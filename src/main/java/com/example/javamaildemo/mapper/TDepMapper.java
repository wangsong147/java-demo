package com.example.javamaildemo.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.javamaildemo.entity.TDep;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author JK
* @description 针对表【t_dep】的数据库操作Mapper
* @createDate 2023-02-27 14:07:24
* @Entity com.example.javamaildemo.entity.TDep
*/
@Mapper
public interface TDepMapper extends BaseMapper<TDep> {
    int deleteByDepIdAndDepName(@Param("depId") Long depId, @Param("depName") String depName);
}




