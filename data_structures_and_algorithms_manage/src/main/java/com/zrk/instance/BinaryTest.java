package com.zrk.instance;

/**
 * This is Description：判断一个数是否是2的N次方
 *
 * @author zhangrenkun
 * @date 2020/10/25
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class BinaryTest {

    public static void main(String[] args) {
        test01(8);
    }

    private static void test01(int i) {
        if ((i & (i - 1)) == 0) {
            System.out.println("[" + i + "]属于2的N次方");
        } else {
            System.out.println("这个数字不属于2的N次方");
        }
    }

}
