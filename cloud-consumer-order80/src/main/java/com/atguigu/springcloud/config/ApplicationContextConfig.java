package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author wrmeng
 * @create 2023-05-28 -11:11
 **/
@Configuration
public class ApplicationContextConfig {

    @Bean
    // @LoadBalanced // 赋予RestTemplate负载均衡能力，才能通过微服务名称进行调用服务，默认的轮训
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
