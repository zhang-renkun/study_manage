package com.zrk.array;

/**
 * This is Description：数据结构--数组
 *
 * @author zhangrenkun
 * @date 2020/10/26
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class ArrayTest {

    private int size;// 数组的长度
    private int data[];
    private int index;// 当前已经存在的数据大小

    public ArrayTest(int size) {
        this.size = size;
        this.data = new int[size];
        this.index = 0;
    }

    public void print() {
        System.out.println("index:" + index);
        for (int i = 0; i < index; i++) {
            System.out.println(data[i] + " ");
        }
    }

    public void insert(int loc, int n) {
        if (index++ < size) {
            for (int i = size - 1; i > loc; i--) {
                data[i] = data[i - 1];// 当前数据往后移
            }
            data[loc] = n;
        }
    }

    public void delete(int loc) {
        for (int i = loc; i < size; i++) {
            if (i != size - 1) {
                data[i] = data[i + 1];
            } else {
                data[i] = 0;
            }
        }
        index--;
    }

    public void update(int loc, int n) {
        data[loc] = n;
    }

    public int get(int loc) {
        return data[loc];
    }

    public static void main(String[] args) {

    }
}
