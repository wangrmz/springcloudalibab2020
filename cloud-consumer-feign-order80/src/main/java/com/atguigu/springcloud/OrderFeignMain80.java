package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // 激活组件  开启客户端openfeign的负载均衡
public class OrderFeignMain80 {

	public static void main(String[] args) {
		SpringApplication.run(OrderFeignMain80.class, args);
	}

}
