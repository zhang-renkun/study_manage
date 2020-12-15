package com.zrk.redislock;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RedisLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisLockApplication.class, args);
    }

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.144.3:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

}
