package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;


/**
 * @author wrmeng
 * @create 2023-05-31 -14:29
 *
 * 将用户的请求平均分配到多个服务器上，从而达到系统的高可用HA
 * ribbon的本地负载均衡客户端 vs nginx服务端负载均衡
 * nginx服务端负载均衡，客户端所有请求都交给nginx,由nginx实现转发请求，负载均衡是由服务端实现
 * 属于集中式
 *
 * ribbon的本地负载均衡，在调用服务接口时候，
 * 会在注册中心上获取注册信息服务列表之后缓存到jvm本地，从而本地实现rpc远程调用服务技术
 * 进程内，逻辑集成到消费房，消费房从服务注册中心获知由那些地址可用，然后从中选取合适的的服务器
 * ribbon只是一个类库，集成于消费方进程，消费方通过它获取到服务提供方的地址
 *
 * 工作原理：2步骤
 * 1、首先选择eurekaserver,优先选择在同一个区域内负载比较少的server
 * 2、根据用户指定的策略，从server的服务注册表中选择一个地址
 *
 * 一句话：就是ribbon+resttemplate调用
 *
 * IRule:7种 默认使用轮训
 *
 *
 **/

public interface LoadBalancer {

    /**
     * 收集微服务上有多少台能提供服务的实例
     * @param serviceInstances
     * @return
     */
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
