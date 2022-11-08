package com.atguigu.springcloud.controller;

import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 杨璨玮
 * @Date: 2022/11/8
 * @Description:
 */
@RestController
/*
3355通过访问3344获得配置内容这个手段有缺点：
GitHub上的更新3344能及时获取(刷新即可获取)
而3355在GitHub更新后如果不重启服务，那仍然为旧内容
针对这种情况，
1.配置yml文件的暴露监控端点
2.controller中添加@RefreshScope
两步操作可进行优化，优化后通过提交post请求刷新资源，不必再重启服务
 */
@RefreshScope  //实现业务的刷新
//cmd窗口中运维人员输入post请求刷新 -> curl -X POST "http://localhost:3355/actuator/refresh"
public class ConfigClientController {
    //此处对读取到GitHub中dev配置config.info信息
    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")
    public String getConfigInfo(){
        return configInfo;
    }
}
