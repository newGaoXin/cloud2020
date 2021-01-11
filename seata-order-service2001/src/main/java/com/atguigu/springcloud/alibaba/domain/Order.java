package com.atguigu.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;

    // 用户id
    private Long userId;

    // 产品id
    private Long product_id;

    // 数量
    private int count;

    // 金额
    private BigDecimal money;

    // 订单状态：0：创建中; 1：已完结
    private int status;
}
