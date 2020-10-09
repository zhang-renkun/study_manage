package com.zrk.builder.es;

/**
 * This is Description
 *
 * @author zhangrenkun
 * @date 2020/10/09
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class BuilderTest {

    public static void main(String[] args) {
        Product.Builder builder = new Product.Builder().name("lisi").age("20");
        builder.desc("this is");
        Product lisi = builder.build();
        System.out.println(lisi);
    }

}

/**
 * 实例对象
 */
class Product {

    private final String name;
    private final String age;
    private final String desc;
    private final String remark;

    public Product(String name, String age, String desc, String remark) {
        this.name = name;
        this.age = age;
        this.desc = desc;
        this.remark = remark;
    }

    /**
     * 通过静态内部类来配合不可变对象
     */
    static class Builder {
        private String name;
        private String age;
        private String desc;
        private String remark;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(String age) {
            this.age = age;
            return this;
        }

        public Builder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public Builder remark(String remark) {
            this.remark = remark;
            return this;
        }

        Product build() {
            return new Product(this.name, this.age, this.desc, this.remark);
        }
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
}
