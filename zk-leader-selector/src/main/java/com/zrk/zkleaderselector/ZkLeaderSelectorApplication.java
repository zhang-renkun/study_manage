package com.zrk.zkleaderselector;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class ZkLeaderSelectorApplication {

    // 客户端
    private static final String CONNECT_STR = "192.168.109.200:2181";
    // 重试机制
    private static RetryPolicy retryPolicy = new ExponentialBackoffRetry(5 * 1000, 10);

    private static CuratorFramework curatorFramework;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        String appName = System.getProperty("appName");

        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(CONNECT_STR, retryPolicy);
        ZkLeaderSelectorApplication.curatorFramework = curatorFramework;
        curatorFramework.start();

        /**
         * 分布式锁实现选举机制
         */
        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            public void takeLeadership(CuratorFramework client) throws Exception {
                System.out.println(" I' m leader now . i'm , " + appName);
                TimeUnit.SECONDS.sleep(15);
            }
        };

        LeaderSelector selector = new LeaderSelector(curatorFramework, "/cachePreHeat_leader", listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();
        countDownLatch.await();
    }
}
