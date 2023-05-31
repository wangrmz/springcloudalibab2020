package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wrmeng
 * @create 2023-05-30 -23:09
 *
 *  zookeeper服务节点是临时节点，在心跳发送时间之内没有收到回复会直接剔除服务
 *  ACP:CP原理
 *
 *  zookeeper作为注册中心不多，集群的配置也很简单
 **/

@SpringBootApplication
@EnableDiscoveryClient  // 该注解用于向consul或者zookeeper作为注册中心时注册服务
public class PaymentMain8004 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain8004.class,args);
    }
}
