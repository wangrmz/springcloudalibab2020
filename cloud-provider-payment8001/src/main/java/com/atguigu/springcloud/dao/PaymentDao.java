package com.atguigu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.atguigu.springcloud.entities.Payment;

/**
 * @author wrmeng
 * @create 2023-05-21 -13:45
 **/

@Mapper
public interface PaymentDao {

    public  int create(Payment payment);

    public  Payment getPaymentById(@Param("id") Long id);



}
