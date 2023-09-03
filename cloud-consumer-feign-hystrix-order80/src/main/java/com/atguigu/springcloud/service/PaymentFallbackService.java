package com.atguigu.springcloud.service;


import org.springframework.stereotype.Component;

/**
 * @author wrmeng
 * @create 2023-09-03 -10:56
 **/
@Component
public class PaymentFallbackService  implements  PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "PaymentFallbackService  fall back-paymentInfo_OK ,~~~~";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "PaymentFallbackService  fall back-paymentInfo_TimeOut ,~~~~";
    }
}
