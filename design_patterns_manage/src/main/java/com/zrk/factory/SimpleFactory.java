package com.zrk.factory;

/**
 * This is Description：工厂方法模式
 *
 * @author zhangrenkun
 * @date 2020/10/09
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class SimpleFactory {

    public static void main(String[] args) {
        ConcreteProductA concreteProductA = new ConcreteProductA();
        ConcreteProductB concreteProductB = new ConcreteProductB();
        System.out.println(concreteProductA.createProduct());
        System.out.println(concreteProductB.createProduct());
    }

}

// 工程方法的具体实现
class ConcreteProductA extends Application {

    @Override
    Product createProduct() {
        return new ProductA();
    }
}

// 工程方法的具体实现
class ConcreteProductB extends Application {

    @Override
    Product createProduct() {
        return new ProductB();
    }
}

// 公共抽象方法
abstract class Application {

    abstract Product createProduct();

}

// 稳定接口
interface Product {
    public void method1();
}

// 具体实现A
class ProductA implements Product {

    public ProductA() {
        System.out.println("this is ProductA");
    }

    @Override
    public void method1() {
        System.out.println("this is ProductA.method1()");
    }
}

// 具体实现B
class ProductB implements Product {

    public ProductB() {
        System.out.println("this is ProductB");
    }

    @Override
    public void method1() {
        System.out.println("this is ProductB.method1()");
    }
}
