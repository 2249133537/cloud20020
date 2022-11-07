package com.atguigu.myrule;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/19
 * @Description:
 */
/*
要更换负载均衡策略首先不能在@ComponentScan扫描的包内
选择好策略后，在主启动类上添加@RibbonClient注解
RoundRobinRule  轮询
RandomRule  随机
RetryRule  先轮询，如果服务获取失败再
WeightedResponseTimeRule  响应速度越快，实际选择权重越高
BestAvailableRule  先过滤掉由于多次访问处于断路器跳闸状态的服务,再选择并发量最小的
AvailabilityFilteringRule  先过滤掉故障实例，再选择并发量小的实例
ZoneAvoidanceRule  复合判断server所在区域的性能和server的可用性选择服务器
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule myRule(){
        return new RandomRule(); //定义为随机
//        return new AvailabilityFilteringRule();
    }
}
