package com.example.javamaildemo.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean(name = "pgKeyGenerator")
    public PostgreKeyGenerator postgreKeyGenerator() {
        return new PostgreKeyGenerator();
    }

    @Bean(name = "dbConfig")
    public GlobalConfig.DbConfig dbConfig() {
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setKeyGenerator(postgreKeyGenerator());
        return dbConfig;
    }

    @Bean(name = "globalConfig")
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setDbConfig(dbConfig());
        return globalConfig;
    }
}
