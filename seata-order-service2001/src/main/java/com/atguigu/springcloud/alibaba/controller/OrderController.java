package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.domain.Result;
import com.atguigu.springcloud.alibaba.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public Result create(Order order){
        orderService.create(order);
        return new Result(200,"订单创建成功");
    }
}
