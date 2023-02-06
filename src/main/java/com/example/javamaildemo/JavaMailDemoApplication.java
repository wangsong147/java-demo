package com.example.javamaildemo;

import com.example.javamaildemo.vo.MailVo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class JavaMailDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaMailDemoApplication.class, args);

        List<Integer> integers = new ArrayList<>();
        integers.add(5);
        integers.add(6);
        integers.add(1);
        integers.add(4);
        integers.add(2);
        integers.add(3);
        integers.stream().sorted(Comparator.comparing(x -> x)).forEach(System.out::println);

        List<MailVo> mailVos = new ArrayList<>();
        mailVos.add(new MailVo(5L,"from","to"));
        mailVos.add(new MailVo(6L,"from","to"));
        mailVos.add(new MailVo(1L,"from","to"));
        mailVos.add(new MailVo(4L,"from","to"));
        mailVos.stream().sorted(Comparator.comparing(MailVo::getId)).forEach(System.out::println);
    }

}
