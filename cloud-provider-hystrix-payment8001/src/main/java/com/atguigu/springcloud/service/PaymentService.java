package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author wrmeng
 * @create 2023-09-02 -09:50
 **/

@Service
public class PaymentService {

    /**
     * 正常访问OK
     *
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_OK ，id:  " + id + "\t" + "哈哈哈～";
    }

    /**
     * #############################################################
     * 服务降级
     * #############################################################
     *
     */

    /**
     * 3s钟之内是正常逻辑，超过就执行我们的回调方法兜底
     * 热部署插件 devtools 对java代码的改动明显，但是对@hystrixCommand内属性的修改建议重启微服务
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        //int timeNumber = 5;
        // int age=10 / 0;
//        try {
//            TimeUnit.SECONDS.sleep(timeNumber);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_OK ，id:  " + id + "\t" + "哈哈哈～" + "   耗时（秒）：" + timeNumber;
        return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_OK ，id:  " + id + "\t" + "哈哈哈～" + "   耗时（秒）：";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {

        return "线程池： " + Thread.currentThread().getName() + "   8001支付侧系统繁忙或者运行报错，请稍后再试 ，id:  " + id + "\t" + " o(x_x)o"  ;
    }


    /**
     * #############################################################
     * 服务熔断
     * #############################################################
     *
     */

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if(id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();// 使用hutool工具类，==不带-的UUID.randomUUID().toString()


        return Thread.currentThread().getName()+"\t"+"调用成功，流水号: " + serialNumber;
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " +id;
    }

}
