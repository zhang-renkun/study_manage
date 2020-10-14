package com.zrk.strategy;

/**
 * This is Description
 *
 * @author zhangrenkun：策略模式
 * @date 2020/10/14
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class StrategyTest {
    public static void main(String[] args) {
        Zombie zombie = new NormalZombie1();
        zombie.display();
        System.out.println(":::::::: 默认行为 :::::::::");
        zombie.move();
        zombie.attack();
        System.out.println(":::::::: 转变行为 :::::::::");
        zombie.setAttackable(new HitAttack());
        zombie.attack();

        System.out.println("------------------- 完美分割线 ------------------");

        Zombie dance = new DanceZombie();
        dance.display();
        System.out.println(":::::::: 默认行为 :::::::::");
        dance.move();
        dance.attack();
        System.out.println(":::::::: 转变行为 :::::::::");
        dance.setMoveable(new DanceMove());
        dance.setAttackable(new HitAttack());
        dance.move();
        dance.attack();
    }
}

/**
 * 将公共行为抽象
 */
interface Moveable {
    void move();
}

interface Attackable {
    void attack();
}

/**
 * 基本实例
 */
abstract class Zombie {
    // 一般表现
    abstract public void display();

    // 具备行为能力
    Moveable moveable;
    Attackable attackable;

    // 公共行为
    abstract void move();

    abstract void attack();

    public Zombie(Moveable moveable, Attackable attackable) {
        this.moveable = moveable;
        this.attackable = attackable;
    }

    // get and set 方便切换行为
    public Moveable getMoveable() {
        return moveable;
    }

    public void setMoveable(Moveable moveable) {
        this.moveable = moveable;
    }

    public Attackable getAttackable() {
        return attackable;
    }

    public void setAttackable(Attackable attackable) {
        this.attackable = attackable;
    }
}

/**
 * 行为move中表现1：一步一步移动
 */
class StepByStepMove implements Moveable {
    @Override
    public void move() {
        System.out.println("一步一步移动");
    }
}

/**
 * 行为move中表现2：太空步
 */
class DanceMove implements Moveable {
    @Override
    public void move() {
        System.out.println("太空步");
    }
}

/**
 * 行为attack中表现1：咬
 */
class BiteAttack implements Attackable {
    @Override
    public void attack() {
        System.out.println("咬");
    }
}

/**
 * 行为attack中表现2：打
 */
class HitAttack implements Attackable {
    @Override
    public void attack() {
        System.out.println("打");
    }
}

/**
 * 实例1
 */
class NormalZombie1 extends Zombie {

    /**
     * 无参构造默认行为
     */
    public NormalZombie1() {
        super(new StepByStepMove(), new BiteAttack());
    }

    public NormalZombie1(Moveable moveable, Attackable attackable) {
        super(moveable, attackable);
    }

    @Override
    public void display() {
        System.out.println("我是普通僵尸");
    }

    @Override
    void move() {
        moveable.move();
    }

    @Override
    void attack() {
        attackable.attack();
    }
}

/**
 * 实例2
 */
class DanceZombie extends Zombie {
    public DanceZombie() {
        super(new StepByStepMove(), new BiteAttack());
    }

    public DanceZombie(Moveable moveable, Attackable attackable) {
        super(moveable, attackable);
    }

    @Override
    public void display() {
        System.out.println("我是舞王僵尸");
    }

    @Override
    void move() {
        moveable.move();
    }

    @Override
    void attack() {
        attackable.attack();
    }
}