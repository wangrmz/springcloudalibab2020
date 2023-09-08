package com.atguigu.springcloud.alibaba.domain;

import lombok.Data;

/**
 * @author wrmeng
 * @create 2023-09-08 -13:32
 **/

@Data
public class Storage {

    private Long id;

    /**
     * 产品id
     */
    private Long productId;

    /**
     * 总库存
     */
    private Integer total;

    /**
     * 已用库存
     */
    private Integer used;

    /**
     * 剩余库存
     */
    private Integer residue;
}


