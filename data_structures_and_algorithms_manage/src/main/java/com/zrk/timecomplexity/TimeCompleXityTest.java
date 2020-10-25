package com.zrk.timecomplexity;

/**
 * This is Description：时间复杂度
 *
 * @author zhangrenkun
 * @date 2020/10/25
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class TimeCompleXityTest {

    public static void main(String[] args) {
        /**
         * 常数
         */
        int a = 1;// 运行1次--O(1)

        for (int i = 0; i < 3; i++) {
            a = a + 1;// 运行3次--时间复杂度仍然是O(1)
        }

        /**
         * 对数
         * x=log2n--O(logn)
         */
        int n = Integer.MAX_VALUE;
        int i = 1;
        while (i <= n) {
            i = i * 2;
        }

        /**
         * 线性
         */
        for (i = 0; i < n; i++) {
            a = a + 1;// n是未知的--O(n)   n是已知的--O(1)
        }

        for (i = 0; i < n; i++) {// 运行n次
            for (int j = 1; j < n; j++) {
                a = a + 1;// O(n^2)
            }
        }
    }
}
