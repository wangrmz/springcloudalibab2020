package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrmeng
 * @create 2023-09-02 -16:15
 **/
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentInfo_Global_FallbackMethod")
public class OrderHystirxController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        return paymentHystrixService.paymentInfo_OK(id);
    }



//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
//    })
     @GetMapping("/consumer/payment/hystrix/timeout/{id}")
     @HystrixCommand
     public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        int age = 10 / 0;
        return paymentHystrixService.paymentInfo_TimeOut(id);
    }

    /**
     * fallback和我们业务系统方法混合在一起，耦合度太高了，代码膨胀：
     * 1、需要通用处理：统一的和自定义的分开
     * 2、避免代码膨胀
     *
     * @param id
     * @return
     */
    public String paymentInfo_TimeOutFallbackMethod(Integer id) {

        return "我是消费者80，对方支付系统繁忙请10秒后充实或者自己运行出错请检查自己 o(x_x)o";
    }

    /**
     * 下面是全局fallback
     */
    public String paymentInfo_Global_FallbackMethod() {
        return "Global,对方支付系统繁忙请10秒后充实或者自己运行出错请检查自己 o(x_x)o";
    }
}
