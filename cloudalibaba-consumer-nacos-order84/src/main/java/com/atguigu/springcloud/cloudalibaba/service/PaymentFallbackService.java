package com.atguigu.springcloud.cloudalibaba.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author wrmeng
 * @create 2023-09-07 -22:11
 **/

@Component
public class PaymentFallbackService implements PaymentService
{
    @Override
    public CommonResult paymentSQL(Long id)
    {
        return new CommonResult(444,"服务降级返回,没有该流水信息",new Payment(id, "errorSerial......"));
    }
}
