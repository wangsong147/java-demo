package com.example.javamaildemo;

import com.example.javamaildemo.service.MailService;
import com.example.javamaildemo.vo.MailVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import javax.mail.MessagingException;
import java.net.InetAddress;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
class JavaMailDemoApplicationTests {
    @Autowired
    JavaMailSender mailSender;

//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    JdbcOperations jdbcOperations;

    @Value("{spring.mail.username}")
    String from;

    @Autowired
    MailService mailService;

    @Test
    void testStr() {
    }

    @Test
    void demo() {
        String ss = "ss";
        boolean b = "abcdefgh".regionMatches(true, 2, ss, 3, 4);
        StringBuffer we = new StringBuffer();
        System.out.println(Integer.decode("99"));
        String rep = "wangwzsls";
        System.out.println(rep.replace('w', ' '));
        System.out.println(rep.replace("w", ""));

        IntStream.range(1, 10).limit(5).forEach(System.out::println);
        IntStream.of(1, 2, 3).forEach(System.out::println);

        List<Integer> integers = Arrays.asList(1, 2, 3);
        Stream.generate(() -> integers).limit(4).forEach(System.out::println);
        // 时间戳
        long l = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        LocalDateTime time2localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(l), ZoneId.systemDefault());
        System.out.println("时间戳：" + l);
        System.out.println("localdatetime：" + time2localDateTime);


        // Date <-> LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.of("GMT+8"));
        Date date = Date.from(LocalDateTime.now().toInstant(ZoneOffset.of("+8")));
        System.out.println("LocalDateTime：" + localDateTime);
        System.out.println("Date：" + date);

    }

    public static void main(String[] args) throws Exception {
        String fileName = "../a/file/name/test/";
        Path path = Paths.get(fileName);
        System.out.println(path.getFileName().toString());
        System.out.println("\n");

        URL url = new URL("https://www.baidu.com/data");
        if (!url.getProtocol().startsWith("http") || !url.getProtocol().startsWith("https"))
            throw new RuntimeException("不是http或https协议");
        InetAddress inetAddress = InetAddress.getByName(url.getHost());
        if (inetAddress.isAnyLocalAddress() || inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress()) {
            throw new RuntimeException("112");
        }
    }

    @Test
    public void sendTemplateMailTest() throws MessagingException {
        String to = "ws18740278017@163.com";
        String subject = "Springboot 发送 模版邮件";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", "wangsong");
        mailService.sendTemplateMail(to, subject, paramMap, "RegisterSuccess");
    }

    @Test
    void testSendEmail() throws Exception {
//        String to = "1148791948@qq.com";
        String to = "ws18740278017@163.com";
        String subject = "Springboot 发送 HTML 图片邮件";
        String content =
                "<h2>Hi~</h2>" +
                "<p>第一封 Springboot HTML 图片邮件</p>" +
                "<br/>" +
                "<img src=\"cid:img01\" /><img src=\"cid:img02\" />";
        String imgPath1 = "R:\\1_ws_projects\\java-demo\\src\\main\\resources\\ai写实2.png";
        String imgPath2 = imgPath1.replaceAll("2\\.",".");
        Map<String, String> imgMap = new HashMap<>();
        imgMap.put("img01", imgPath1);
        imgMap.put("img02", imgPath2);
        mailService.sendImgMail(to, subject, content, imgMap);
    }

    @Test
    void testCollection() {
        MailVo mailVo1 = new MailVo();
        mailVo1.setFromMail("from_1");
        mailVo1.setTo("to_1");

        MailVo mailVo2 = new MailVo();
        mailVo2.setFromMail("from_2");
        mailVo2.setTo("to_2");

        MailVo mailVo3 = new MailVo();
        mailVo3.setFromMail("from_3");
        mailVo3.setTo("to_3");

        Collection<MailVo> collection = new ArrayList<>(20);
        collection.add(mailVo1);
        collection.add(mailVo2);
        collection.add(mailVo3);
        System.out.println(collection);


        String s = collection.stream().map(MailVo::getTo).reduce("", (a, b) -> a + b);  // 头部拼接一个str
        System.out.println(s);

        for (MailVo mailVo : collection) {
            mailVo.setFromMail("0");
            mailVo.setTo("0");
        }
        System.out.println(collection);

        // ================================================================================================
        Collection<Integer> integers1 = new ArrayList<>();
        integers1.add(1);
        integers1.add(2);
        integers1.add(3);
        integers1.add(4);
        integers1.add(4);
        System.out.println(integers1);
        Set<Integer> collect = integers1.stream().collect(Collectors.toSet()); //去重
        System.out.println(collect);
        if (true) return;

        Collection<Integer> integers2 = new ArrayList<>();
        integers2.add(new Integer(1));
        integers2.add(new Integer(2));

        integers2.add(new Integer(3));
        integers2.add(new Integer(4));


        for (Integer integer : integers2) {
            int i = integer.intValue();
        }
        System.out.println(integers2);


    }


    @Test
    void calendar() {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }


}
