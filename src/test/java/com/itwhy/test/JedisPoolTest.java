package com.itwhy.test;

import com.itwhy.jedis.util.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * @ClassName: {NAME}
 * @Auther: why
 * @Date: 2023/07/04 17 10
 * @Version: v1.0
 */
public class JedisPoolTest {

    private Jedis jedis;

    @BeforeEach
    void setUp(){
        //建立连接
        jedis = JedisConnectionFactory.getJedis();
        //选择库
        jedis.select(0);
    }

    @Test
    void testString() {
        //存入数据
        String result = jedis.set("name1", "虎哥");
        System.out.println(result);
        //取出数据
        String name = jedis.get("name1");
        System.out.println("name1 = " + name);
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
    void tearDown() {
        if(jedis != null) {
            jedis.close();
        }
    }
}
