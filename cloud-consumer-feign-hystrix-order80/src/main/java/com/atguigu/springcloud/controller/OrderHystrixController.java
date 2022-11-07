package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/22
 * @Description:
 */
@RestController
@Slf4j
//未特别指定的降级处理就走下面的全局fallback
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    /*
    进行高并发测试：
    发现服务均需要等待，有时还会有超时错误
    正因为这些出现了故障、表现不佳状况
    才有了降级、容错、限流等技术产生
     */
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    @GetMapping("consumer/payment/hystrix/timeout/{id}")
    //pom导入hystrix依赖，再启动类激活@EnableHystrix
    /**
     * fallbackMethod为兜底方法
     * @HystrixProperty内设置可等待时间
     */
//    @HystrixCommand(fallbackMethod ="paymentInfoTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2500")
//            //此处规定，XX秒钟以内的处理时间为正常，不正常则启用paymentInfo_TimeOutHandler兜底
//    })
    @HystrixCommand  //未指定fallback，用全局fallback
    public String paymentInfo_TimeOut(@PathVariable("id")Integer id){
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }
    public String paymentInfoTimeOutFallbackMethod(@PathVariable("id")Integer id){
        return "我是消费者80，对方支付系统繁忙，请稍后重试";
    }

    //全局fallback
    public String payment_Global_FallbackMethod(){
        return "80Global异常处理，请稍后重试";
    }
}
