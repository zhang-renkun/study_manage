package com.zrk.builder;


/**
 * This is Description：建造者模式
 *
 * @author zhangrenkun
 * @date 2020/10/09
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class DefuldBuilder {

    public static void main(String[] args) {
        DefaultConcreteProductBuilder defaultConcreteProductBuilder = new DefaultConcreteProductBuilder();
        Director director = new Director(defaultConcreteProductBuilder);
        Product product = director.makeProduct("zhangsan", "19", "this is", "remark");
        System.out.println(product);
    }

}

/**
 * 根据需求建造个别属性
 */
interface ProductBuilder {
    void builderProductName(String name);

    void builderProductAge(String age);

    void builderProductDesc(String desc);

    void builderProductRemark(String remark);

    Product build();
}

/**
 * 默认建造
 * 有特别的建造方法只需要改造这个方法即可
 */
class DefaultConcreteProductBuilder implements ProductBuilder {
    private String name;
    private String age;
    private String desc;
    private String remark;

    @Override
    public void builderProductName(String name) {
        this.name = name;
    }

    @Override
    public void builderProductAge(String age) {
        this.age = age;
    }

    @Override
    public void builderProductDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public void builderProductRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Product build() {
        return new Product(this.name, this.age, this.desc, this.remark);
    }
}

/**
 * 建造者
 */
class Director {
    private ProductBuilder builder;

    public Director(ProductBuilder builder) {
        this.builder = builder;
    }

    public Product makeProduct(String name, String age, String desc, String remark) {
        builder.builderProductName(name);
        builder.builderProductAge(age);
        builder.builderProductDesc(desc);
        builder.builderProductRemark(remark);
        return builder.build();
    }
}

/**
 * 实例对象
 */
class Product {
    private String name;
    private String age;
    private String desc;
    private String remark;

    public Product() {
    }

    public Product(String name, String age, String desc, String remark) {
        this.name = name;
        this.age = age;
        this.desc = desc;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", desc='" + desc + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
