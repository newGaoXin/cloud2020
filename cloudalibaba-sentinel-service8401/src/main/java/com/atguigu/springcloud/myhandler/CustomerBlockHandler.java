package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.commons.Result;

public class CustomerBlockHandler {

    public static Result handleException(BlockException exception) {
        return new Result(200, "按客戶自定义  global-------handleException1");
    }

    public static Result handleException2(BlockException exception) {
        return new Result(200, "按客戶自定义  global-------handleException2");
    }
}
