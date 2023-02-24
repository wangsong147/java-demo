package com.example.javamaildemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
@Component
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("servlet创建");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)     {
        log.info("servlet销毁");
    }
}
