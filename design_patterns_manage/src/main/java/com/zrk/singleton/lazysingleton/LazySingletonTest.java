package com.zrk.singleton.lazysingleton;

/**
 * This is Description：懒汉式
 *
 * @author zhangrenkun
 * @date 2020/09/29
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class LazySingletonTest {

    public static void main(String[] args) {
        /**
         * 但线程访问下创建的两个实例对象时一样的
         */
//        test01();
        /**
         * 多线程访问下存在线程安全问题
         */
//        test02();
        /**
         * 最简单的解决方式：在构造方法上面加 synchronized
         * 即使这样，在多线程的情况下也会出现多条线程实例化时加多个锁
         */
//        test03();
        /**
         * 双重check优化：在内部类上面加 synchronized
         */
        test04();
    }

    private static void test04() {
        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();

        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();
    }

    private static void test03() {
        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();

        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();
    }

    private static void test02() {
        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();

        new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(instance);
        }).start();
    }

    private static void test01() {
        LazySingleton instance = LazySingleton.getInstance();
        LazySingleton instance1 = LazySingleton.getInstance();
        System.out.println(instance == instance1);
    }


}

class LazySingleton {
    /**
     * volatile 防止重排序
     */
    private volatile static LazySingleton instance;

    public LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null)
                    instance = new LazySingleton();
            }
        }
        return instance;
    }
}
