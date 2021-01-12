package com.atguigu.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private int code;

    private String msg;

    private T data;

    public Result(int code,String msg){
        this(code,msg,null);
    }
}
