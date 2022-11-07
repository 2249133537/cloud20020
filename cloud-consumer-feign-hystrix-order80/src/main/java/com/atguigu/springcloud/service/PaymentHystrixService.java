package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/22
 * @Description:
 */
@Component
//从CLOUD-PAYMENT-HYSTRIX-SERVICE找到服务，如果对应服务出错，由PaymentFallbackService.class负责兜底
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE",fallback = PaymentFallbackService.class)
public interface PaymentHystrixService {
/*
如果下列方法正常，那就会正常找到方法
如果异常，那就会跳往实现类，返回fallback信息
 */
    @GetMapping("payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id);

    @GetMapping("payment/hystrix/timeout/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id);
}
