package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/18
 * @Description:
 */
@Configuration
public class ApplicationContextConfig {
    @Bean

    //负载均衡开启，服务提供者集群，默认轮询。
    // 如果使用自定义编写策略，则要注释掉此代码，否则自写的策略无效
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
