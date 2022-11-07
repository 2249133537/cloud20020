package com.atguigu.springcloud.service;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/22
 * @Description:
 */
public interface PaymentService {
    public String paymentInfo_OK(Integer id);

    public String paymentInfo_TimeOut(Integer id);

    public String paymentInfo_TimeOutHandler(Integer id);

    public String paymentCircuitBreaker(Integer id);

    public String paymentCircuitBreaker_fallback(Integer id);
}
