package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/18
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    /*
    此处主要导包，不是网飞，而是spring
    主启动类要加上@EnableDiscoveryClient
     */
    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("payment/create")
//    @RequestMapping("payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result =  paymentService.create(payment);
        log.info("插入结果--->"+result);
        if (result>0){
            //带给前台成功编码，信息，结果集
            return  new CommonResult(200,"数据插入成功"+serverPort,result);
        }else {
            return new CommonResult(444,"数据插入失败",null);
        }
    }

    @GetMapping("payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment != null){
            //带给前台成功编码，信息，结果集
            return  new CommonResult(200,"数据查询成功"+serverPort,payment);
        }else {
            return new CommonResult(444,"数据查询失败",null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery(){
        //遍历所有服务列表清单--->服务名称
        List<String> services = discoveryClient.getServices();
        for (String element: services) {
            log.info("element: "+element);
        }

        //获取单种服务的指定信息：一种服务可能有多个同名服务同时进行，一个服务多个实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping("/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("payment/feign/timeout")
    public String paymentFeignTimeout(){
        //暂停3秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return serverPort;
    }

    //zipkin+sleuth
    @GetMapping("payment/zipkin")
    public String paymentZipkin(){
        return "Hi, I am paymentZipkin server fall back, welcome to cloud";
    }
}
