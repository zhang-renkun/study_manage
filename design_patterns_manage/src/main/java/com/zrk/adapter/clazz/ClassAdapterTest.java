package com.zrk.adapter.clazz;

/**
 * This is Description：类适配器
 *
 * @author zhangrenkun
 * @date 2020/10/12
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class ClassAdapterTest {

    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.output5v();
    }
}

class Adaptee {
    public int output220v() {
        return 220;
    }
}

interface Target {
    int output5v();
}

class Adapter extends Adaptee implements Target {

    @Override
    public int output5v() {
        int i = output220v();
        // ......
        System.out.println("原始电压：" + i + "v，转换电压：5v");
        return 5;
    }
}