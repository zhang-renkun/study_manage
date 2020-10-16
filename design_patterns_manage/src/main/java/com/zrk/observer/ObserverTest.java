package com.zrk.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * This is Description：观察者模式
 *
 * @author zhangrenkun
 * @date 2020/10/17
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class ObserverTest {
    public static void main(String[] args) {
        Subject subject = new Subject();
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        subject.addObserver(task1);
        subject.addObserver(task2);
        subject.notifyObserver("zhangrk");
        System.out.println("--------------- 完美分割线 --------------");
        subject.removeObserver(task1);
        subject.notifyObserver("zhangrk123");
    }
}

/**
 * 主题对象/任务容器
 */
class Subject {
    // 容器
    private List<Observer> container = new ArrayList<>();

    // add
    public void addObserver(Observer observer) {
        container.add(observer);
    }

    // remove
    public void removeObserver(Observer observer) {
        container.remove(observer);
    }

    /**
     * 事件发布
     */
    public void notifyObserver(Object object) {
        for (Observer item : container) {
            item.update(object);
        }
    }
}

/**
 * 观察者
 */
interface Observer {
    void update(Object object);
}

class Task1 implements Observer {
    @Override
    public void update(Object object) {
        System.out.println("task1 :" + object);
    }
}

class Task2 implements Observer {
    @Override
    public void update(Object object) {
        System.out.println("task2 :" + object);
    }
}
