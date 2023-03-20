package com.example.javamaildemo.service.impl;

import com.example.javamaildemo.dao.BaseDao;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("commonService")
public class CommonService extends BaseDao {
    public Map<String, Object> setUp(){
        return super.dataListOfMap("select ckey, cvalue from setup_info where ckey in (?,?)", "sales_division_code","sales_division_name");
    }
}
