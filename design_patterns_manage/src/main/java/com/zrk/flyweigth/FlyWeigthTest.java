package com.zrk.flyweigth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is Description:享元模式
 *
 * @author zhangrenkun
 * @date 2020/10/11
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class FlyWeigthTest {

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3, 4, TreeFactory.getTree("zhangsan", "123456"));
        TreeNode treeNode2 = new TreeNode(5, 4, TreeFactory.getTree("zhangsan", "123456"));

        TreeNode treeNode3 = new TreeNode(13, 14, TreeFactory.getTree("lisi", "123456"));
        TreeNode treeNode4 = new TreeNode(15, 14, TreeFactory.getTree("lisi", "123456"));
    }
}

/**
 * 树节点
 */
class TreeNode {
    private int x;
    private int y;
    private Tree tree;

    @Override
    public String toString() {
        return "TreeNode{" +
                "x=" + x +
                ", y=" + y +
                ", tree=" + tree +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public TreeNode(int x, int y, Tree tree) {
        this.x = x;
        this.y = y;
        this.tree = tree;
    }
}

/**
 * 树实例
 */
class Tree {
    private final String name;
    private final String data;

    @Override
    public String toString() {
        return "Tree{" +
                "name='" + name + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

    public Tree(String name, String data) {
        System.out.println("Tree:name=[" + name + "],data=[" + data + "]");
        this.name = name;
        this.data = data;
    }
}

/**
 * 树工厂
 */
class TreeFactory {
    private static Map<String, Tree> map = new ConcurrentHashMap<>();

    public static Tree getTree(String name, String data) {
        if (map.containsKey(name)) {
            return map.get(name);
        }
        Tree tree = new Tree(name, data);
        map.put(name, tree);
        return tree;
    }
}
