package com.zrk.entity;

/**
 * This is Description
 *
 * @author zhangrenkun
 * @date 2020/12/14
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public class Order {

    private Integer id;
    private Integer pid;
    private String userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
