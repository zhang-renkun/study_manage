package com.zrk.strategy;

/**
 * This is Description：策略模式
 *
 * @author zhangrenkun
 * @date 2020/10/14
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class ZombieTest {

    public static void main(String[] args) {
        AbstractZombie normal = new NormalZombie();
        AbstractZombie flag = new FlagZombie();
        AbstractZombie bighead = new BigHeadZombie();

        normal.display();
        normal.move();
        normal.attack();

        System.out.println("----------------------- 完美分割线 -----------------------");

        flag.display();
        flag.move();
        flag.attack();

        System.out.println("----------------------- 完美分割线 -----------------------");

        bighead.display();
        bighead.move();
        bighead.attack();
    }
}

/**
 * 公共属性
 * 模拟僵尸
 */
abstract class AbstractZombie {

    public abstract void display();

    public void attack() {
        System.out.println("咬");
    }

    public void move() {
        System.out.println("一步一步移动");
    }
}

/**
 * 实例1
 */
class NormalZombie extends AbstractZombie {

    @Override
    public void display() {
        System.out.println("我是普通僵尸");
    }
}

/**
 * 实例2
 */
class FlagZombie extends AbstractZombie {

    @Override
    public void display() {
        System.out.println("我是旗手僵尸");
    }
}

/**
 * 实例3
 */
class BigHeadZombie extends AbstractZombie {

    @Override
    public void display() {
        System.out.println("我是大头僵尸");
    }

    /**
     * 重写父类方法
     * 重新定义新特性
     */
    @Override
    public void attack() {
        /**
         * ........
         * 实际业务可能会很复杂
         */
        System.out.println("头撞");
    }
}
