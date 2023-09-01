package springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wrmeng
 * @create 2023-05-28 -10:52
 *
 * 三个注册中心的异同点
 * eureka ap  http  java  可视化web   好死不如赖活着，自我保护机制
 * consul cp  http/dns   go 可视化web
 * zookeeper cp  客户端  java  无
 *  cap:
 *  c:强一致性
 *  a:可用性
 *  p:分区容错性--分布式架构必须保证，要不是cp，或者安ap
 *  cap理论关注粒度是数据，而不是整理系统设计
 *
 *  redis:就是cp
 *
 *  ap架构 ：网络分区出现后，为了保证可用性，系统B可以返回旧值，
 *  结论：违背了一致性c的要求，只满足可用性和分区容错
 *
 *  cp架构：网络分区出现后，为了保证一致性，旧必须拒绝请求
 *  结论：违背了高可用性a的要求，只满足一致性和分区容错
 *
 *
 **/

@RestController
@Slf4j
public class OrderController {


//    public static final String PAYMENT_URL = "http://localhost:8001";


    // 找微服务名称
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    public static final String INVOKE_URL = "http://consul-provider-payment";


    /**
     * 客户端工具
     */
    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/consumer/payment/consul")
    public String getPaymentInfo() {

        String result = restTemplate.getForObject(INVOKE_URL+"/payment/consul",String.class);
        return result;
    }



}
