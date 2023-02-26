package com.example.javamaildemo;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * PostgreSQL 代码生成
 *
 * @author lanjerry
 * @since 3.5.3
 */
public class PostgreSQLGeneratorTest {
    public static void main(String[] args) {
        FastAutoGenerator.create("url", "username", "password")
                .globalConfig(builder -> {
                    builder.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.baomidou.mybatisplus.samples.generator") // 设置父包名
                            .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_simple") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
//        // 数据源配置
//        DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
//                .Builder("jdbc:postgresql://localhost:5432/postgres?currentSchema=wstest&characterEncoding=utf-8&serverTimezone=GMT%2B8",
//                "postgres",
//                "postgres")
//                .build();
//
//        StrategyConfig strategyConfig = new StrategyConfig.Builder().build();
//
//        GlobalConfig globalConfig = new GlobalConfig.Builder().build();
//
//        AutoGenerator generator = new AutoGenerator(DATA_SOURCE_CONFIG);
//        generator.strategy(strategyConfig);
//        generator.global(globalConfig);
//        generator.execute();
    }
}