package com.example.javamaildemo.mapper;

import com.example.javamaildemo.entity.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author JK
* @description 针对表【payment】的数据库操作Mapper
* @createDate 2023-03-03 10:59:37
* @Entity com.example.javamaildemo.entity.Payment
*/
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {

}




