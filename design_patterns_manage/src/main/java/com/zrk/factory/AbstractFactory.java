package com.zrk.factory;

/**
 * This is Description：抽象工厂模式
 *
 * @author zhangrenkun
 * @date 2020/10/09
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class AbstractFactory {

    public static void main(String[] args) {
        IBaseUtils baseUtils = new MysqlBaseUtils();
        baseUtils.getConnect().connect();
        baseUtils.getCommand().command();
    }

}

/**
 * 公共连接接口
 */
interface IConnection {
    void connect();
}

class MysqlConnetion implements IConnection {

    @Override
    public void connect() {
        System.out.println("Mysql Connetion");
    }
}

/**
 * 公共执行接口
 */
interface ICommand {
    void command();
}

class MysqlCommand implements ICommand {

    @Override
    public void command() {
        System.out.println("Mysql Command");
    }
}


interface IBaseUtils {

    IConnection getConnect();

    ICommand getCommand();
}

class MysqlBaseUtils implements IBaseUtils {

    @Override
    public IConnection getConnect() {
        return new MysqlConnetion();
    }

    @Override
    public ICommand getCommand() {
        return new MysqlCommand();
    }
}

