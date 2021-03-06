package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {

    // 新增订单
    public void create(Order order);

    // 修改订单状态
    public void updateStatus(@Param("userId") Long userId, @Param("status") int status);
}
