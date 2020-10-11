package com.zrk.singleton.innerclasssingleton;

import java.io.*;

/**
 * This is Description：静态内部类单例
 *
 * @author zhangrenkun
 * @date 2020/09/29
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class InnerClassSingletonTest {

//    public static void main(String[] args) {
//        new Thread(() -> {
//            InnerClassSingleton instance = InnerClassSingleton.getInstance();
//            System.out.println(instance);
//        }).start();
//        new Thread(() -> {
//            InnerClassSingleton instance = InnerClassSingleton.getInstance();
//            System.out.println(instance);
//        }).start();
//    }


    /**
     * 反射攻击
     *
     * @param args
     */
//    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        // 获取实例
//        Constructor<InnerClassSingleton> declaredConstructor = InnerClassSingleton.class.getDeclaredConstructor();
//        // 开放权限
//        declaredConstructor.setAccessible(true);
//        InnerClassSingleton innerClassSingleton = declaredConstructor.newInstance();
//
//        InnerClassSingleton instance = InnerClassSingleton.getInstance();
//        System.out.println(innerClassSingleton == instance);
//
//    }

    /**
     * 序列化
     */
    public static void main(String[] args) throws Exception {
        InnerClassSingleton instance = InnerClassSingleton.getInstance();

        // 序列化
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("testSerializable"));
        oos.writeObject(instance);
        oos.close();

        // 反序列化
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testSerializable"));
        InnerClassSingleton o = (InnerClassSingleton) ois.readObject();

        System.out.println(instance == o);
    }
}

class InnerClassSingleton implements Serializable {

    static final long serialVersionUID = 43L;

    /**
     * 静态内部类
     */
    private static class InnerClassHolder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    public InnerClassSingleton() {
        // TODO 防止反射攻击
        if (InnerClassHolder.instance != null)
            throw new RuntimeException("单例不允许多个实例");
    }

    public static InnerClassSingleton getInstance() {
        // TODO 在调用这个方法的时候再去加载InnerClassHolder类，从而才获取实例
        return InnerClassHolder.instance;
    }

    /**
     * 指定一个替代方案来从流中获取实例
     */
    Object readResolve() throws ObjectStreamException{
        return InnerClassHolder.instance;
    }
}
