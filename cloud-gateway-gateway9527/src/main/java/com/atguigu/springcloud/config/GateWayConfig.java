package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/26
 * @Description:
 */
@Configuration
public class GateWayConfig {
    /*
    配置了一个id为route-name的路由规则
    当访问地址hppt://localhost:9527/guonei时会自动转发到http://news.baidu.guonei
    可配置多个路由
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //此连接指向百度国内新闻
        routes.route("path_route_baidu", r -> r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .build();
        return routes.build();
    }
    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        //此连接指向百度国内新闻
        routes.route("path_route_bilibili", r -> r.path("/bilibili").uri("https://www.bilibili.com/"))
                .build();
        return routes.build();
    }
}
