package com.zrk.controller;

import com.zrk.service.OrderService;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is Description
 *
 * @author zhangrenkun
 * @date 2020/12/14
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
@RestController
public class TestController {

    @Autowired
    private OrderService orderService;

    @Value("${server.port}")
    private String port;


    @Autowired
    CuratorFramework curatorFramework;

    @PostMapping("/stock/deduct")
    public Object reduceStock(Integer id) throws Exception {
        // new 一个互斥锁
        InterProcessMutex interProcessMutex = new InterProcessMutex(curatorFramework, "/product_" + id);
        try {
            // 加锁，获取不到循环等待
            interProcessMutex.acquire();
            orderService.reduceStock(id);
        } catch (Exception e) {
            if (e instanceof RuntimeException) {
                throw e;
            }
        } finally {
            // 释放锁
            interProcessMutex.release();
        }
        return "ok:" + port;
    }
}
