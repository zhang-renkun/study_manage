package com.zrk.classload;

/**
 * This is Description：类加载
 *
 * @author zhangrenkun
 * @date 2020/10/18
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class ClassLoadingTest {

    static {
        System.out.println("load ClassLoadingTest");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("load test");
        B b = null;  //B不会加载，除非这里执行 new B()
    }
}

class A {
    static {
        System.out.println("load A");
    }

    public A() {
        System.out.println("initial A");
    }
}

class B {
    static {
        System.out.println("load B");
    }

    public B() {
        System.out.println("initial B");
    }
}
