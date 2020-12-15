package com.zrk.mapper;

import com.zrk.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * This is Description
 *
 * @author zhangrenkun
 * @date 2020/12/14
 * @Slogan 以后的你一定会感谢现在努力的自己！
 */
public interface ProductMapper {

    @Select(" select * from product where id=#{id}  ")
    Product getProduct(@Param("id") Integer id);

    @Update(" update product set stock=stock-1    where id=#{id}  ")
    int deductStock(@Param("id") Integer id);
}
