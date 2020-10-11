package com.zrk.singleton.enumsingleton;

/**
 * This is Description：枚举式单例
 *
 * @author zhangrenkun
 * @date 2020/09/29
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public enum EnumSingleton {
    INSTANCE;
    public void print() {
        System.out.println(this.hashCode());
    }
}

class EnumTest {
    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.INSTANCE;
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        instance.print();
        instance1.print();
    }
}
