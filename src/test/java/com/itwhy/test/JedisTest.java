package com.itwhy.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @ClassName: {NAME}
 * @Auther: why
 * @Date: 2023/07/04 15 56
 * @Version: v1.0
 */
public class JedisTest {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        //1.建立连接
        jedis = new Jedis("192.168.200.101", 6379);
        //2.设置密码
        jedis.auth("123321");
        //3.选择库
        jedis.select(0);
    }


    @Test
    void testString() {
        //存入数据
        String result = jedis.set("name", "虎哥");
        System.out.println(result);
        //取出数据
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testHash() {
        //存入数据
        jedis.hset("user1", "name", "Jack");
        jedis.hset("user1", "age", "25");

        //获取
        Map<String, String> user1 = jedis.hgetAll("user1");
        System.out.println(user1);
    }

    @AfterEach
    void tearDown(){
        if(jedis != null) {
            jedis.close();
        }
    }
}
