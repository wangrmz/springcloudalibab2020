package com.atguigu.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
     * 3s钟之内是正常逻辑，超过就执行我们的回调方法兜底
     * 热部署插件 devtools 对java代码的改动明显，但是对@hystrixCommand内属性的修改建议重启微服务
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="3600")
    })
    public String paymentInfo_TimeOut(Integer id) {
        //int timeNumber = 5;
         int age=10 / 0;
//        try {
//            TimeUnit.SECONDS.sleep(timeNumber);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        try {
            TimeUnit.MILLISECONDS.sleep(13000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_OK ，id:  " + id + "\t" + "哈哈哈～" + "   耗时（秒）：" + timeNumber;
        return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_OK ，id:  " + id + "\t" + "哈哈哈～" + "   耗时（秒）：";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {

        return "线程池： " + Thread.currentThread().getName() + "   8001支付侧系统繁忙或者运行报错，请稍后再试 ，id:  " + id + "\t" + " o(x_x)o"  ;
    }


}
