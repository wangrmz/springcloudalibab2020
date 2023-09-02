package com.atguigu.springcloud.service;

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

    public String paymentInfo_TimeOut(Integer id) {
        int timeNumber = 5;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池： " + Thread.currentThread().getName() + "   paymentInfo_OK ，id:  " + id + "\t" + "哈哈哈～" + "   耗时（秒）：" + timeNumber;
    }


}
