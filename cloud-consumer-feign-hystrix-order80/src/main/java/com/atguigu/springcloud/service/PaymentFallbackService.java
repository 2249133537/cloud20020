package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/23
 * @Description:
 */
@Component  //注册组件
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "paymentInfo_OK-----fallback";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "paymentInfo_TimeOut-----fallback";
    }
}
