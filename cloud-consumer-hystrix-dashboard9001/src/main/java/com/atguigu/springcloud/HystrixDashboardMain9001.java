package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard // 启用hystrix的图形化界面
public class HystrixDashboardMain9001 {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardMain9001.class, args);
	}

}
