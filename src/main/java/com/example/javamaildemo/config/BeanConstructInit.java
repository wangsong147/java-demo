package com.example.javamaildemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BeanConstructInit {
    private JdbcTemplate jdbcTemplate;

    public BeanConstructInit(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        log.info("************{}", jdbcTemplate);
    }
}
