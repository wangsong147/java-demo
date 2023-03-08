package com.example.javamaildemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@ServletComponentScan
public class JavaMailDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaMailDemoApplication.class, args);
    }

}
