package com.atguigu.springcloud.controller;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author wrmeng
 * @create 2023-05-21 -14:56
 * <p>
 * 前后端分离，回传的数格式，约定格式
 **/
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;


    // 进行区分
    @GetMapping(value = "/payment/zk")
    public String paymentzk() {
       return "springcloud with zookeeper: "+serverPort+"\t"+ UUID.randomUUID().toString();
    }


}
