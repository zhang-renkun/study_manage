package com.zrk.adapter.object;

/**
 * This is Description：对象适配器
 *
 * @author zhangrenkun
 * @date 2020/10/12
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class ObjectAdapterTest {

    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();

        Target target = new Adapter(adaptee);
        target.output5v();
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

class Adapter implements Target {

    /**
     * 组合
     */
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    /**
     * 转换
     *
     * @return
     */
    @Override
    public int output5v() {
        int i = adaptee.output220v();
        // ......
        System.out.println("原始电压：" + i + "v，转换电压：5v");
        return 5;
    }
}
