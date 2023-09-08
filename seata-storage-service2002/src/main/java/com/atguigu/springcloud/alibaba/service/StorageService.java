package com.atguigu.springcloud.alibaba.service;

/**
 * @author wrmeng
 * @create 2023-09-08 -13:33
 **/

public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}


