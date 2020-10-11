package com.zrk.singleton.hungrysingleton;

/**
 * This is Description：饿汉模式
 *
 * @author zhangrenkun
 * @date 2020/09/29
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class HungrySingletonTest {

    public static void main(String[] args) {
        HungrySingleton instance = HungrySingleton.getInstance();
        HungrySingleton instance1 = HungrySingleton.getInstance();
        System.out.println(instance == instance1);
    }
}

class HungrySingleton {
    private static HungrySingleton instance = new HungrySingleton();

    public HungrySingleton() {
    }

    public static HungrySingleton getInstance() {
        return instance;
    }
}