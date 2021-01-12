package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.alibaba.domain.Order;

public interface OrderService {

    // 新增订单
    public void create(Order order);
}
