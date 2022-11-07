package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/22
 * @Description:
 */
@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        log.info("****result:"+result);
        return result;
    }

    /**
     * 再用JMeter工具模拟20000个请求访问该方法，再用一个请求访问上一个ok接口，模拟高并发
     * 结果->包括上一个服务，两个服务都变慢了
     * @param id
     * @return
     */
    @GetMapping("payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id)   {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("****result:"+result);
        return result;
    }

    //========服务熔断
    @GetMapping("payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("***result："+result);
        return result;
    }
}
