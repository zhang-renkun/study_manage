package com.zrk.classload;

import com.sun.crypto.provider.DESKeyFactory;
import sun.misc.Launcher;

import java.net.URL;

/**
 * This is Description：几种类加载器简单认识
 *
 * @author zhangrenkun
 * @date 2020/10/18
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class JDKClassLoaderTest {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(DESKeyFactory.class.getClassLoader());
        System.out.println(JDKClassLoaderTest.class.getClassLoader());

        System.out.println("-------------------- 完美分割线 ----------------------");
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassLoader.getParent();
        System.out.println("this is bootstrapLoader:" + bootstrapLoader);
        System.out.println("this is extClassLoader:" + extClassLoader);
        System.out.println("this is appClassLoader:" + appClassLoader);

        System.out.println("-------------------- 完美分割线 ----------------------");
        System.out.println("bootstrapLoader加载一下文件:");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (int i = 0; i < urLs.length; i++) {
            System.out.println(urLs[i]);
        }

        System.out.println("-------------------- 完美分割线 ----------------------");
        System.out.println("extClassLoader加载一下文件:");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println("-------------------- 完美分割线 ----------------------");
        System.out.println("appClassLoader加载以下文件：");
        System.out.println(System.getProperty("java.class.path"));
    }
}
