package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wrmeng
 * @create 2023-05-28 -10:52
 **/

@RestController
@Slf4j
public class OrderController {


//    public static final String PAYMENT_URL = "http://localhost:8001";


    // 找微服务名称
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";


    /**
     * 客户端工具
     */
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {

        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }


    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {

        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }
}
