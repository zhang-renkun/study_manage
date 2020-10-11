package com.zrk.prototype;

import java.io.*;

/**
 * This is Description：原型模式
 *
 * @author zhangrenkun
 * @date 2020/10/11
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        BaseInfo zhangsan = new BaseInfo("zhangsan");

        Product product = new Product("part1", "part2", "part3", zhangsan);
        System.out.println("原始对象：" + product);
        Product clone = product.clone();
        System.out.println("克隆对象：" + clone);

        System.out.println("--------------------------------------------------------------------");

        product.getBaseInfo().setName("lisi");
        System.out.println("原始对象：" + product);
        System.out.println("克隆对象：" + clone);
    }

}

/**
 * 新增复杂属性
 * 同样实现Cloneable接口
 *
 * 实现Serializable
 */
class BaseInfo implements Cloneable, Serializable {

    private static final long serialVersionUID = 42L;

    private String name;

    @Override
    protected BaseInfo clone() throws CloneNotSupportedException {
        return (BaseInfo) super.clone();
    }

    @Override
    public String toString() {
        return super.hashCode() + "] BaseInfo{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseInfo(String name) {
        this.name = name;
    }

    public BaseInfo() {
    }
}

/**
 * 原始对象
 * 实现Cloneable接口，重写clone方法
 * <p>
 * 复杂类型属性clone要改造clone方法
 *
 * 实现Serializable反序列化实现深克隆
 */
class Product implements Cloneable, Serializable {

    private static final long serialVersionUID = 42L;

    private String part1;
    private String part2;
    private String part3;
    private BaseInfo baseInfo;

//    @Override
//    protected Product clone() throws CloneNotSupportedException {
//        Product clone = (Product) super.clone();
//        BaseInfo clone1 = this.baseInfo.clone();
//        clone.setBaseInfo(clone1);
//        return clone;
//    }

    /**
     * java序列化实现深克隆
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Product clone() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream)) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        try {
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            return (Product) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return super.hashCode() + "] Product{" +
                "part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                ", part3='" + part3 + '\'' +
                ", baseInfo=" + baseInfo +
                '}';
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public String getPart3() {
        return part3;
    }

    public void setPart3(String part3) {
        this.part3 = part3;
    }

    public Product() {
    }

    public Product(String part1, String part2, String part3, BaseInfo baseInfo) {
        this.part1 = part1;
        this.part2 = part2;
        this.part3 = part3;
        this.baseInfo = baseInfo;
    }
}
