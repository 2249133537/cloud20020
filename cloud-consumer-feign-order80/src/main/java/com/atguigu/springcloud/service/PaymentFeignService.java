package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/21
 * @Description:
 */
//通过controller接口跳转的方法找到下列接口
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //1.先找到服务名
public interface PaymentFeignService {

   //跳转controller找到服务名下的对应方法->此处找到8001对外暴露的对应的方法payment/get/{id}
   /*
   controller接口调用service接口，service接口的GetMapping再调用客户端对外暴露的对应方法
    */
   @GetMapping("payment/get/{id}")
   public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);


   @GetMapping("payment/feign/timeout")
   public String paymentFeignTimeout();
}
