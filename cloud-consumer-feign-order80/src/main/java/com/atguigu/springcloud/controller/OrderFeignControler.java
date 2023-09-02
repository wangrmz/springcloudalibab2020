package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wrmeng
 * @create 2023-09-02 -08:06
 **/
@RestController
@Slf4j
public class OrderFeignControler {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return  paymentFeignService.getPaymentById(id);
    }

    /**
     * openfeign 底层是ribbon,openfeign客户端一般默认等待1分钟
     * 可以提供等待时间--进行超时控制
     * @return
     */
    @GetMapping(value = "/consumer/payment/feign/timeout")
    public  String paymentFeignTimeout() {
        return  paymentFeignService.paymentFeignTimeout();
    }


}
