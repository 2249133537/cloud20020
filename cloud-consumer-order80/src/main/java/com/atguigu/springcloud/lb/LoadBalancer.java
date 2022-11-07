package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/20
 * @Description:
 */
/*
自定义手写轮询
1.先注释掉springcloud.config中的@LoadBalanced注解
 */
public interface LoadBalancer {
    //获取服务数组
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
