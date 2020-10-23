package com.zrk.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This is Description：oom
 *
 * @author zhangrenkun
 * @date 2020/10/23
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class OOMTest {

    private static List<User> list = new ArrayList<>();

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (true) {
            list.add(new User(i++, UUID.randomUUID().toString()));
            new User(j--, UUID.randomUUID().toString());
        }
    }

    static class User {
        private int id;
        private String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            /**
             * 可对即将回收的对象进行自救
             * TODO 不推荐使用 有异常直接在try里面处理
             */
            list.add(this);
            System.out.println("关闭资源，id=[" + id + "]即将被回收");
        }
    }
}


