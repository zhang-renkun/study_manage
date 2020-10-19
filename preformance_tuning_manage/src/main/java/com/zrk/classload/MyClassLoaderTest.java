package com.zrk.classload;

import com.sun.xml.internal.bind.v2.TODO;

import java.io.FileInputStream;
import java.lang.reflect.Method;

/**
 * This is Description：自定义类加载器
 *
 * @author zhangrenkun
 * @date 2020/10/19
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class MyClassLoaderTest {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        private byte[] loadByte(String name) throws Exception {
            name = name.replaceAll("\\.", "/");
            FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();
            return data;
        }

        protected Class<?> findClass(String name) throws ClassNotFoundException {
            try {
                byte[] data = loadByte(name);
                //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组。
                return defineClass(name, data, 0, data.length);
            } catch (Exception e) {
                e.printStackTrace();
                throw new ClassNotFoundException();
            }
        }

        /**
         * 打破双亲委派机制
         * 重写类加载方法，实现自己的逻辑
         *
         * @param name
         * @param resolve
         * @return
         * @throws ClassNotFoundException
         */
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
//                    TODO 不委派给双亲
//                    long t0 = System.nanoTime();
//                    try {
//                        if (parent != null) {
//                            c = parent.loadClass(name, false);
//                        } else {
//                            c = findBootstrapClassOrNull(name);
//                        }
//                    } catch (ClassNotFoundException e) {
//                        // ClassNotFoundException thrown if class not found
//                        // from the non-null parent class loader
//                    }

                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();
                        // TODO 因为会加载到Object等核心类，所以要排除这些，只加载自定义的一些类
                        if (!name.startsWith("com.zrk.jvm")) {
                            // 如果不是自定义类路径开头的class文件，由其父类加载器加载
                            c = this.getParent().loadClass(name);
                        } else {
                            // 否则自行加载
                            c = findClass(name);
                        }
                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }

        public static void main(String args[]) throws Exception {
            // 初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载器设置为应用程序类加载器AppClassLoader
            // TODO 自定义要加载的类路径
            MyClassLoader classLoader = new MyClassLoader("");
            // TODO 创建自定义的几级目录 将User类的复制类User1.class丢入该目录
            Class clazz = classLoader.loadClass("com.zrk.jvm.User1");
            Object obj = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("sout", null);
            method.invoke(obj, null);
            System.out.println(clazz.getClassLoader().getClass().getName());

            System.out.println("--------------- 完美分割线 --------------");
            // TODO 模拟tomcat的webappClassLoader加载不同war包不同版本类实现共存和隔离
            MyClassLoader classLoader1 = new MyClassLoader("D:/test1");
            Class clazz1 = classLoader1.loadClass("com.tuling.jvm.User1");
            Object obj1 = clazz1.newInstance();
            Method method1= clazz1.getDeclaredMethod("sout", null);
            method1.invoke(obj1, null);
            System.out.println(clazz1.getClassLoader());
        }
    }
}
