package com.zrk.mapper;

import com.zrk.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

/**
 * This is Description
 *
 * @author zhangrenkun
 * @date 2020/12/14
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public interface OrderMapper {

    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert(" insert into `order`(user_id,pid) values(#{userId},#{pid}) ")
    int insert(Order order);

}
