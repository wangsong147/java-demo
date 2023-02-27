package com.example.javamaildemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(value = "person")// 告诉这个类的属性都是配置文件里的属性，prefix 指定读取配置文件的前缀。
@PropertySource(value = "classpath:person.properties")
public class PersonProperties {
    private String name;
    private Integer age;
    private List<String> hobby;
    private Map<String,String> map;
}
