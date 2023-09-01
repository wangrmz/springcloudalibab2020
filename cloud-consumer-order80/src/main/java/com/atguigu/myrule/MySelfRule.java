package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrmeng
 * @create 2023-05-31 -15:20
 **/
@Configuration
public class MySelfRule {

    @Bean //id就是方法名
    public IRule myRule(){
        return new RandomRule();
    }
}
