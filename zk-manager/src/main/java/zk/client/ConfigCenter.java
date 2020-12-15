package zk.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ConfigCenter {

    private final static String CONNECT_STR = "192.168.144.3:2181";

    private final static Integer SESSION_TIMEOUT = 30 * 1000;

    /**
     * zk客户端
     */
    private static ZooKeeper zooKeeper = null;

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        log.info("开始建立连接");
        /**
         * 建立zk连接
         */
        zooKeeper = new ZooKeeper(CONNECT_STR, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.None
                        && event.getState() == Event.KeeperState.SyncConnected) {
                    log.info("连接已建立");
                    // 主线程继续执行
                    countDownLatch.countDown();
                }
            }
        });
        // 主线程等待
        countDownLatch.await();

        MyConfig myConfig = new MyConfig();
        myConfig.setKey("anykey");
        myConfig.setName("anyName");

        // 对象序列化
        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes = objectMapper.writeValueAsBytes(myConfig);
        // 创建zk节点
        String s = zooKeeper.create("/myconfig", bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        Watcher watcher = new Watcher() {
            @SneakyThrows
            @Override
            public void process(WatchedEvent event) {
                // 若节点发生改变
                if (event.getType() == Event.EventType.NodeDataChanged
                        && event.getPath() != null && event.getPath().equals("/myconfig")) {
                    log.info(" PATH:{}  发生了数据变化", event.getPath());
                    // 获取变化后的数据同时加上监听
                    byte[] data = zooKeeper.getData("/myconfig", this, null);
                    // 反序列化
                    MyConfig newConfig = objectMapper.readValue(new String(data), MyConfig.class);
                    log.info("数据发生变化: {}", newConfig);
                }
            }
        };
        // 获取当前节点下的数据信息  添加监听
        byte[] data = zooKeeper.getData("/myconfig", watcher, null);
        MyConfig originalMyConfig = objectMapper.readValue(new String(data), MyConfig.class);
        log.info("原始数据: {}", originalMyConfig);

        TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
    }
}
