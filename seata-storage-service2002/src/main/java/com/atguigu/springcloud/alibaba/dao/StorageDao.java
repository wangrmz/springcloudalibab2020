package com.atguigu.springcloud.alibaba.dao;

/**
 * @author wrmeng
 * @create 2023-09-08 -13:32
 **/
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {

    /**
     * 扣减库存
     */
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}


