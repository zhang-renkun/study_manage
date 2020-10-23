package com.zrk.gc;

/**
 * This is Description：jvm信息打印
 *
 * @author zhangrenkun
 * @date 2020/10/23
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class GCTest {

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6;
        allocation1 = new byte[60000 * 1024];

        allocation2 = new byte[8000 * 1024];
        allocation3 = new byte[1000 * 1024];
        allocation4 = new byte[1000 * 1024];
        allocation5 = new byte[1000 * 1024];
        allocation6 = new byte[1000 * 1024];
    }
}
