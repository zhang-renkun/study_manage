package com.zrk.instance;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * This is Description：计算全国人口各个年龄段的数量
 *
 * @author zhangrenkun
 * @date 2020/10/26
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class AgesTest {

    public static void main(String[] args) throws Exception {
        String str = "";
        String fileName = "";

        long start = System.currentTimeMillis();
        InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        int tot = 0;
        int data[] = new int[200];
        while ((str = br.readLine()) != null) {
            int age = Integer.valueOf(str);
            data[age]++;
            tot++;
        }
        System.out.println("总共的数据：" + tot);

        for (int i = 0; i < 200; i++) {
            System.out.println(i + ":" + data[i]);
        }
        System.out.println("计算花费时间：" + (System.currentTimeMillis() - start) + "ms");
    }
}
