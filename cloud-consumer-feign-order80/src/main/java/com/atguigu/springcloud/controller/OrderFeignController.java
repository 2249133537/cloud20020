package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/21
 * @Description:
 */
@RestController
@Slf4j
//由此跳转实际的业务接口->8001
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    //服务调用接口，将跳转service中
    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping("consumer/payment/feign/timeout")
    public String paymentFeignTimeout(){
        //openFeign-ribbon客户端一般默认等待1秒，超过1秒则报错，这里故意设置3秒让其报错
        return paymentFeignService.paymentFeignTimeout();
    }
}
