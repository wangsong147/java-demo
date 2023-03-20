package com.example.javamaildemo.common;

import com.example.javamaildemo.service.impl.CommonService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Slf4j
@Component // 加入spring，利用有参构造，在项目启动时就查数据库动态加载常量
public class Constant {
    public final static Integer DELETED = 1;
    public final static Integer UN_DELETED = 0;
    public final static LocalDateTime CREATE_TIME = LocalDateTime.now();
    public final static LocalDateTime UPDATE_TIME = LocalDateTime.now();
    public final static String JWT_KEY = "myKey";

    // 项目初始化加载配置
    public static String SalesDivisionCode = null;
    public static String SalesDivisionName = null;

    // 有参构造的参数会自动寻找ioc中的bean对象，不需要autowired也可
    // Qualifier配合上面的component一起使用，在注入CommonService的时候注入名字是commonService的CommonService对象
    public Constant(@Qualifier("commonService") CommonService commonService) {
        Map<String, Object> r = commonService.setUp();
        log.info("{}", r); // code和name都没有会返回空{},只要有一个有值就会进入判断
        if (null != r && !r.isEmpty()) {
            Constant.SalesDivisionCode = String.valueOf(r.get("sales_division_code") == null ? "PHARMA" : r.get("sales_division_code"));
            Constant.SalesDivisionName = String.valueOf(r.get("sales_division_name") == null ? "PHARMA" : r.get("sales_division_name"));
        }
        log.info("加载配置信息完成");
        log.info("code:{}", SalesDivisionCode);
        log.info("code:{}", SalesDivisionName);
    }

}
