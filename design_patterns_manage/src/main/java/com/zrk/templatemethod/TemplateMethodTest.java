package com.zrk.templatemethod;

/**
 * This is Description：模版方法模式
 *
 * @author zhangrenkun
 * @date 2020/10/16
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class TemplateMethodTest {

    public static void main(String[] args) {
        AbstractClass abstractClass = new SubClass();
        abstractClass.operation();
    }
}

/**
 * 定义一个操作的算法骨架
 */
abstract class AbstractClass {
    public void operation() {
        // 具体业务处理操作....   es:
        System.out.println("pre ...");
        System.out.println("step1");
        System.out.println("step2");

        templateMethod();
    }

    /**
     * 每个子类自己的算法实现
     */
    abstract protected void templateMethod();
}

class SubClass extends AbstractClass {

    @Override
    protected void templateMethod() {
        System.out.println("SubClass exectued..");
    }
}
