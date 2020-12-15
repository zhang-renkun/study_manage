package com.zrk.redislock;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * This is Description
 *
 * @author zhangrenkun
 * @date 2020/11/30
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private Redisson redisson;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/deduct_stock")
    public String deductStock() {
        String lockKey = "lockKey";
        // 获取锁对象
        RLock lock = redisson.getLock(lockKey);
        try {
            // 加锁，续命
            lock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                logger.info("减扣成功，剩余库存：" + realStock);
            } else {
                logger.info("减扣失败，库存不足");
            }
        } finally {
            // 释放锁
            lock.unlock();
        }
        return "end";
    }
}
