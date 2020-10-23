package com.zrk.beansize;

import org.openjdk.jol.info.ClassLayout;

/**
 * This is Description：对象大小
 * 添加jol-core包
 *
 * @author zhangrenkun
 * @date 2020/10/23
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class BeanSizeTest {

    public static void main(String[] args) {
        System.out.println("----------------- start。。。 ------------------");

        ClassLayout classLayout = ClassLayout.parseInstance(new Object());
        System.out.println(classLayout.toPrintable());

        System.out.println("----------------- 完美分割线 ------------------");
        ClassLayout classLayout1 = ClassLayout.parseInstance(new int[]{});
        System.out.println(classLayout1.toPrintable());

        System.out.println("----------------- 完美分割线 ------------------");
        ClassLayout classLayout2 = ClassLayout.parseInstance(new A());
        System.out.println(classLayout2);
    }

    // -XX:+UseCompressedOops           默认开启的压缩所有指针
    // -XX:+UseCompressedClassPointers  默认开启的压缩对象头里的类型指针Klass Pointer
    // Oops : Ordinary Object Pointers
    public static class A {
        //8B mark word
        //4B Klass Pointer   如果关闭压缩-XX:-UseCompressedClassPointers或-XX:-UseCompressedOops，则占用8B
        int id;        //4B
        String name;   //4B  如果关闭压缩-XX:-UseCompressedOops，则占用8B
        byte b;        //1B
        Object o;      //4B  如果关闭压缩-XX:-UseCompressedOops，则占用8B
    }
}
