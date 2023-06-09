package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author wrmeng
 * @create 2023-05-21 -14:50
 **/

public interface PaymentService {

    public  int create(Payment payment);

    public  Payment getPaymentById(@Param("id") Long id);


}
