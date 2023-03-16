//package com.example.javamaildemo;
//
//import com.baomidou.mybatisplus.core.conditions.Wrapper;
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
//import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
//import com.example.javamaildemo.entity.Person;
//import com.example.javamaildemo.entity.RolePlayUser;
//import com.example.javamaildemo.mapper.PersonMapper;
//import com.example.javamaildemo.service.MailService;
//import com.example.javamaildemo.service.PersonService;
//import com.example.javamaildemo.vo.MailVo;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.javamail.JavaMailSender;
//
//import javax.mail.MessagingException;
//import java.net.InetAddress;
//import java.net.URL;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.time.Instant;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.ZoneOffset;
//import java.util.*;
//import java.util.stream.Collectors;
//import java.util.stream.IntStream;
//import java.util.stream.Stream;
//
//@SpringBootTest
//@Slf4j
//class JavaMailDemoApplicationTests {
//    @Autowired
//    JavaMailSender mailSender;
//
//    @Value("{spring.mail.username}")
//    String from;
//
//    @Autowired
//    MailService mailService;
//
//    @Autowired
//    PersonMapper personMapper;
//
//
//    @Autowired
//    PersonService personService;
//
//    @Test
//    void testStr() {
//        Person person = new Person();
//        person.setAge(111);
//        person.setName("dwee");
//        personService.save(person);
//
//        personService.update(new LambdaUpdateWrapper<Person>()
//                .set(Person::getName, "w")
//                .or()
//                .isNull(Person::getId));
//    }
//
//    @Test
//    void demo() {
//        String ss = "ss";
//        boolean b = "abcdefgh".regionMatches(true, 2, ss, 3, 4);
//        StringBuffer we = new StringBuffer();
//        System.out.println(Integer.decode("99"));
//        String rep = "wangwzsls";
//        System.out.println(rep.replace('w', ' '));
//        System.out.println(rep.replace("w", ""));
//
//        IntStream.range(1, 10).limit(5).forEach(System.out::println);
//        IntStream.of(1, 2, 3).forEach(System.out::println);
//
//        List<Integer> integers = Arrays.asList(1, 2, 3);
//        Stream.generate(() -> integers).limit(4).forEach(System.out::println);
//        // 时间戳
//        long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
//        LocalDateTime time2localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneId.systemDefault());
//        System.out.println("时间戳：" + l);
//        System.out.println("localdatetime：" + time2localDateTime);
//
//
//        // Date <-> LocalDateTime
//        LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.of("GMT+8"));
//        Date date = Date.from(LocalDateTime.now().toInstant(ZoneOffset.of("+8")));
//        System.out.println("LocalDateTime：" + localDateTime);
//        System.out.println("Date：" + date);
//
//    }
//
//    public static void main(String[] args) throws Exception {
//        String fileName = "../a/file/name/test/";
//        Path path = Paths.get(fileName);
//        System.out.println(path.getFileName().toString());
//        System.out.println("\n");
//    }
//
//    @Test
//    public void sendTemplateMailTest() throws MessagingException {
//        String to = "ws18740278017@163.com";
//        String subject = "Springboot 发送 模版邮件";
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("username", "wangsong");
//        mailService.sendTemplateMail(to, subject, paramMap, "RegisterSuccess");
//    }
//
//    @Test
//    void testSendEmail() throws Exception {
////        String to = "1148791948@qq.com";
//        String to = "ws18740278017@163.com";
//        String subject = "Springboot 发送 HTML 图片邮件";
//        String content =
//                "<h2>Hi~</h2>" +
//                        "<p>第一封 Springboot HTML 图片邮件</p>" +
//                        "<br/>" +
//                        "<img src=\"cid:img01\" /><img src=\"cid:img02\" />";
//        String imgPath1 = "R:\\1_ws_projects\\java-demo\\src\\main\\resources\\ai写实2.png";
//        String imgPath2 = imgPath1.replaceAll("2\\.", ".");
//        Map<String, String> imgMap = new HashMap<>();
//        imgMap.put("img01", imgPath1);
//        imgMap.put("img02", imgPath2);
//        mailService.sendImgMail(to, subject, content, imgMap);
//    }
//
//}
