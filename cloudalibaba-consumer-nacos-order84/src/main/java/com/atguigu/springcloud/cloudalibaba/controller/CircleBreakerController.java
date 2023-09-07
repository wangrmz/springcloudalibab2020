package com.atguigu.springcloud.cloudalibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.cloudalibaba.service.PaymentService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author wrmeng
 * @create 2023-09-07 -21:10
 **/

@RestController
@Slf4j
public class CircleBreakerController
{
    public static final String SERVICE_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback")// 没有配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //blockHandler负责在sentinel里面配置的降级限流
    //@SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler") //两者都配置：若 blockHandler 和 fallback 都进行了配置，则被限流降级而抛出 BlockException 时只会进入 blockHandler 处理逻辑。
    @SentinelResource(value = "fallback",fallback = "handlerFallback",blockHandler = "blockHandler",exceptionsToIgnore = {IllegalArgumentException.class})// 配置忽略属性，java异常就无法处理
    public CommonResult fallback(@PathVariable Long id)
    {
        CommonResult result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/"+id,CommonResult.class,id);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result.getData() == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }


    /**
     * 处理业务异常
     * @param id
     * @param e
     * @return
     */
    public CommonResult handlerFallback(@PathVariable  Long id,Throwable e) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(444,"兜底异常handlerFallback,exception内容  "+e.getMessage(),payment);
    }

    /**
     * 处理sentinel配置异常
     * @param id
     * @param blockException
     * @return
     */
    public CommonResult blockHandler(@PathVariable  Long id, BlockException blockException) {
        Payment payment = new Payment(id,"null");
        return new CommonResult(445,"blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage(),payment);
    }



    //================OpenFeign：接口调用+注解
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/openfeign/{id}")
    public CommonResult paymentSQL(@PathVariable("id") Long id)
    {
        if(id == 4)
        {
            throw new RuntimeException("没有该id");
        }
        return paymentService.paymentSQL(id);
    }

}






