package com.atguigu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 杨璨玮
 * @Date: 2022/10/16
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

//加上全参和无参，一共有三种构造方法
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
