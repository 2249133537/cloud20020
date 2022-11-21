package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: HeChi
 * @Date: 2022/11/14
 * @Description:
 */
@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA(){
        return "-----testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "-----testB";
    }

    @GetMapping("/testD")
    public String testD(){
//         延迟线程X秒作用 ---->测试降级RT
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "---------testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    //如果未配置blockHandler，则降级方法为Sentinel默认方法，返回默认提示错误页面40X，5XX
    //此为绑定降级方法，这里的资源名与GetMapping的资源名相差一个 / 符号，用以区分
    //如果违背控制台中配置的规则，则启用绑定的降级方法（资源名要绑定@SentinelResource的value）
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2)
    {
        return "testHotKey";
    }
    public String deal_testHotKey(String p1, String p2, BlockException e){
        //sentinel系统默认提示为：Blocked by Sentinel (flow limiting)
        return "testHotKey,deal_testHotKey";
    }
}
