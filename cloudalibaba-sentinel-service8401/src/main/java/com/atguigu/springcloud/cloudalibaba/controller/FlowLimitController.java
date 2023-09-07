package com.atguigu.springcloud.cloudalibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author wrmeng
 * @create 2023-09-07 -10:33
 **/

@RestController
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA()
    {
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB()
    {
        return "------testB";
    }
}


