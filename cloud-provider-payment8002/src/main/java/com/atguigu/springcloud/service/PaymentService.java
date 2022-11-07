package com.atguigu.springcloud.service;


import com.atguigu.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/18
 * @Description:
 */
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
