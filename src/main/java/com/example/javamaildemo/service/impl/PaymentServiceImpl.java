package com.example.javamaildemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.javamaildemo.entity.Payment;
import com.example.javamaildemo.service.PaymentService;
import com.example.javamaildemo.mapper.PaymentMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
* @author JK
* @description 针对表【payment】的数据库操作Service实现
* @createDate 2023-03-03 10:59:37
*/
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment>
    implements PaymentService{
    /**
     * 转帐：a+100元，b-100元
     * 事务应该加在sevice的方法上，以业务为基准，而不是单个方法的事务
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void transferAccounts(){}

}




