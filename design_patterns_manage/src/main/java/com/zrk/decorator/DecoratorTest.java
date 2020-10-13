package com.zrk.decorator;

/**
 * This is Description：装饰者模式
 *
 * @author zhangrenkun
 * @date 2020/10/13
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class DecoratorTest {

    public static void main(String[] args) {
        ComponentService service = new ConreteDecorator2(new ConreteDecorator1(new ComponentServiceImpl()));
        service.operation();
    }
}

/**
 * 基础接口
 */
interface ComponentService {
    void operation();
}

/**
 * 基础接口实现
 */
class ComponentServiceImpl implements ComponentService {

    @Override
    public void operation() {
        System.out.println("拍照");
    }
}

/**
 * 对接口进行装饰
 */
abstract class Decorator implements ComponentService {
    ComponentService service;

    public Decorator(ComponentService service) {
        this.service = service;
    }
}

/**
 * 继承装饰
 */
class ConreteDecorator1 extends Decorator {

    public ConreteDecorator1(ComponentService service) {
        super(service);
    }

    @Override
    public void operation() {
        // 扩展新功能
        System.out.println("添加美颜");
        // 旧方法不能丢弃，可根据实际业务调整流程
        service.operation();
    }
}

class ConreteDecorator2 extends Decorator {

    public ConreteDecorator2(ComponentService service) {
        super(service);
    }

    @Override
    public void operation() {
        // 扩展新功能
        System.out.println("添加滤镜");
        // 旧方法不能丢弃，可根据实际业务调整流程
        service.operation();
    }
}