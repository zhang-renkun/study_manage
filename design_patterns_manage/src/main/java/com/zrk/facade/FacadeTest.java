package com.zrk.facade;

/**
 * This is Description：门面模式
 *
 * @author zhangrenkun
 * @date 2020/10/12
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class FacadeTest {

    public static void main(String[] args) {
        Client1 client1 = new Client1();
        client1.doSomething1();
    }
}

/**
 * 相当于接口抽离
 */
class Facade {
    SubSystem1 subSystem1 = new SubSystem1();
    SubSystem2 subSystem2 = new SubSystem2();

    public void doSomething() {
        subSystem1.method1();
        subSystem2.method2();
    }
}

/**
 * 模拟客户端
 */
class Client1 {
//    SubSystem1 subSystem1 = new SubSystem1();
//    SubSystem2 subSystem2 = new SubSystem2();
//
//    // 类似于注入的形式
//    public void doSomething1() {
//        subSystem1.method1();
//        subSystem2.method2();
//    }

    /**
     * 不再使用以上形式
     */
    Facade facade = new Facade();
    public void doSomething1() {
        facade.doSomething();
    }
}

class Client2 {
    SubSystem1 subSystem1 = new SubSystem1();
    SubSystem2 subSystem2 = new SubSystem2();

    // 类似于注入的形式
    public void doSomething2() {
        subSystem1.method1();
        subSystem2.method2();
    }
}

/**
 * 模拟子系统
 */
class SubSystem1 {
    public void method1() {
        System.out.println("this is SubSystem1.method1()");
    }
}

class SubSystem2 {
    public void method2() {
        System.out.println("this is SubSystem2.method2()");
    }
}
