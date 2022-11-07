package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author: 杨璨玮
 * @Date: 2022/10/21
 * @Description:
 */
@Configuration
public class FeignConfig {
//feign开启日志，配置config后也要配置yml，开启日志

//NONE:默认，不显示日志
//BASIC:仅记录请求方法，url，响应状态码以及执行时间
//HEADERS:除了BASIC中的信息外，还有请求和响应的头信息
//FUll:除了HEADERS中定义的信息外，还有请求和响应的正文及元数据
    @Bean
    Logger.Level feignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
