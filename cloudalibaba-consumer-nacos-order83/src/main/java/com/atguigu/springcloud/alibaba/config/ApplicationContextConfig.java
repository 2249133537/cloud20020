package com.atguigu.springcloud.alibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: HeChi
 * @Date: 2022/11/11
 * @Description:
 */
@Configuration
public class ApplicationContextConfig {
    @Bean
    //RestTemplate+Ribbon实现负载均衡必须要加@LoadBalanced注解，指定使用负载均衡方法
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
