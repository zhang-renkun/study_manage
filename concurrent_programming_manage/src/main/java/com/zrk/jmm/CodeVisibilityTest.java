package com.zrk.jmm;


/**
 * This is Description
 *
 * @author zhangrenkun
 * @date 2020/11/03
 * @SSystem.out.printlnan 以后的你一定会感谢现在努力的自己！
 */
public class CodeVisibilityTest {

    private static boolean initFlag = false;

    private volatile static int counter = 0;

    public static void refresh() {
        System.out.println("refresh data.......");
        initFlag = true;
        System.out.println("refresh data success.......");
    }

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            while (!initFlag) {
                //System.out.println("runing");
                counter++;
            }
            System.out.println("线程：" + Thread.currentThread().getName()
                    + "当前线程嗅探到initFlag的状态的改变");
        }, "threadA");
        threadA.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread threadB = new Thread(() -> {
            refresh();
        }, "threadB");
        threadB.start();
    }
}
