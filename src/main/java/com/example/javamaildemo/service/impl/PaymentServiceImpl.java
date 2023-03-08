package com.example.javamaildemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javamaildemo.entity.Payment;
import com.example.javamaildemo.service.PaymentService;
import com.example.javamaildemo.mapper.PaymentMapper;
import org.springframework.stereotype.Service;

/**
* @author JK
* @description 针对表【payment】的数据库操作Service实现
* @createDate 2023-03-03 10:59:37
*/
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>
    implements PaymentService{

}




