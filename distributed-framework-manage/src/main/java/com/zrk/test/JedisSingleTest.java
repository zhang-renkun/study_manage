package com.zrk.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * This is Description
 *
 * @author zhangrenkun
 * @date 2020/11/26
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class JedisSingleTest {

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig, "192.168.144.3", 6379, 3000, null);

        Jedis jedis = null;

        // 从redis连接吃里拿出一个连接执行命令
        jedis = jedisPool.getResource();
//        jedis.select(0);

        System.out.println(jedis.get("app"));

    }

}
