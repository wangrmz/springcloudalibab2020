package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wrmeng
 * @create 2023-09-01 -16:13
 **/
@Component
// 获取指定的微服务
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")// 使用
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) ;

    @GetMapping(value = "/payment/feign/timeout")
    public  String paymentFeignTimeout();

}

