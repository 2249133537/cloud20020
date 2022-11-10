package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/18
 * @Description:
 */
@RestController
@Slf4j
//使用RestTemplate启用微服务之间的调用，首先要config配置RestTemplate
public class OrderController {
//    public static final String PAYMENT_URL = "http://localhost:8001";
    //服务提供者集群只关注服务名称，同时config中也要加上@LoadBlanced注解，默认轮询
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    //自定义轮询
    @Resource
    private LoadBalancer loadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        //post方式
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    //方式1，一般使用,返回响应体中数据转化的对象，基本可以理解成Json
    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        //get方式
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    //方式2，需要更详细信息时使用，包括响应中的一些重要信息，如响应头，响应状态码，响应体
    @GetMapping("consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPayment2(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }

    //自定义轮询创建
    @GetMapping("consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0){
            return null;
        }
        //获取到CLOUD-PAYMENT-SERVICE的端口
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }

    //zipkin+sleuth
    @GetMapping("consumer/payment/zipkin")
    public String paymentZipkin(){
        //未启用负载均衡时候用这个代码，url形式，端口号+服务接口
//        String result = restTemplate.getForObject("http://locatlhost:8001"+"/payment/zipkin/",String.class);

        //已启用负载均衡，此处用Ribbon负载均衡规则，用服务名调用方式PAYMENT_URL+"/payment/zipkin/"指向服务
        return restTemplate.getForObject(PAYMENT_URL+"/payment/zipkin/",String.class);
//        return restTemplate.getForObject(uri+"/payment/lb",String.class);
    }
}
