package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wrmeng
 * @create 2023-09-01 -14:18
 **/


@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        // 自旋
//        参数：该函数接受两个强制性参数，如下所述：
//        expect:它指定原子对象应为的值。
//        val:如果原子整数等于期望值，则该值指定要更新的值。
//        返回值：该函数返回一个布尔值，成功则返回true，否则返回false。
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        System.out.println("******第几次访问，次数next:" + next);
        return  next;
    }

    /**
     * 负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标  ，每次服务重启动后rest接口计数从1开始。
     * List instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
     * 如：   List [0] instances = 127.0.0.1:8002
     * List [1] instances = 127.0.0.1:8001
     * 8001+ 8002 组合成为集群，它们共计2台机器，集群总数为2， 按照轮询算法原理：
     * 当总请求数为1时： 1 % 2 =1 对应下标位置为1 ，则获得服务地址为127.0.0.1:8001
     * 当总请求数位2时： 2 % 2 =0 对应下标位置为0 ，则获得服务地址为127.0.0.1:8002
     * 当总请求数位3时： 3 % 2 =1 对应下标位置为1 ，则获得服务地址为127.0.0.1:8001
     * 当总请求数位4时： 4 % 2 =0 对应下标位置为0 ，则获得服务地址为127.0.0.1:8002
     * 如此类推......
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        // 获取服务中具体是那个服务
        return serviceInstances.get(index);
    }
}
