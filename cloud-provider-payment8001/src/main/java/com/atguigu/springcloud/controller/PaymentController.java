package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


/**
 * @author wrmeng
 * @create 2023-05-21 -14:56
 * <p>
 * 前后端分离，回传的数格式，约定格式
 **/
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

//    @Resource
//    private DiscoveryClient discoveryClient;

    // 进行区分
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("******插入结果：" + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功,serverPort: "+serverPort, result);
        } else {
            return new CommonResult(444, "插入数据库失败 ", null);
        }
    }

    /**
     * resultful风格
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("*****查询结果：" + payment);
        int len=10/3;
        if (payment != null) {
            return new CommonResult(200, "查询成功,serverPort: " + serverPort, payment);
        } else {
            return new CommonResult(444, "查询失败，没有对应记录，查询ID" + id, null);
        }

    }

//    @GetMapping(value = "/payment/discovery")
//    public Object discovery() {
//
//
//        List<InstanceInfo> instancesById = discoveryClient.getInstancesById("CLOUD-PAYMENT-SERVICE");
//        for (InstanceInfo info : instancesById) {
//            log.info(info.getId()+"\t"+info.getHostName()+"\t"+info.getPort()+"\t");
//        }
//        return  this.discoveryClient;
//    }

   @GetMapping(value = "/payment/lb")
   public  String getPaymentLB(){
        return serverPort;
   }
   @GetMapping(value = "/payment/feign/timeout")
   public  String paymentFeignTimeout(){
       try {
           TimeUnit.SECONDS.sleep(3);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }
       return serverPort;
   }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin()
    {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
