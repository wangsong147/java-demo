package com.example.javamaildemo.dao;

import java.util.HashMap;
import java.util.Map;

public class BaseDao extends Dao {
    public Map<String, Object> dataListOfMap(String sql, Object... params) {
        //
        return getJdbcTemplate().query(sql, rs -> {
            Map<String, Object> r = new HashMap<>();
            // 把每一行的数据封装进map集合里
            while (rs.next()) {
                r.put(rs.getString(1), rs.getObject(2));
            }
            return r;

        }, params);
    }
}
