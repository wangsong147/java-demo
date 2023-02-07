package com.example.javamaildemo;

import com.example.javamaildemo.vo.MailVo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
public class JavaMailDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaMailDemoApplication.class, args);

        List<MailVo> mailVos = new ArrayList<>();
        mailVos.add(new MailVo(4L,"from","to",5));
        mailVos.add(new MailVo(6L,"from","to",1));
        mailVos.add(null);
        mailVos.add(new MailVo(1L,"from","to",1));
        mailVos.add(null);
        mailVos.add(new MailVo(5L,"from","to",4));
        // Comparator静态方法
//        mailVos.stream().sorted(Comparator.comparing(MailVo::getId).reversed()).forEach(System.out::println);System.out.println("\n");
//        // 对象方法
        mailVos.stream().sorted((x,y)->Long.compare(y.getId(),x.getId())).forEach(System.out::println);System.out.println("\n");
//
//        mailVos.stream().sorted((x,y)->y.getAge()-x.getAge()).forEach(System.out::println);
//
        // nullsFirst(): 1.处理null值，避免空指针异常  2.把null排在前面
        System.out.println("=============");
        mailVos.stream().sorted(Comparator.nullsFirst(Comparator.comparing(MailVo::getId).reversed())).forEach(System.out::println);
        // thenComparing(): 前者相等的时候，按后者比较
        // reversed(): 针对的是整个数组进行一个反转
        System.out.println("=============");
        mailVos.stream().sorted(Comparator.comparing(MailVo::getAge).thenComparingLong(MailVo::getId)).forEach(System.out::println);
    }

}
