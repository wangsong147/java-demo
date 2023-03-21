package com.example.javamaildemo;

import com.baomidou.mybatisplus.annotation.TableName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest()
public class RedisTest {


    @Test
    public void test(){
        Jedis jedis = new Jedis("192.168.204.88", 6379);
        String ping = jedis.ping();
        System.out.println("PING:" + ping);
    }


}
