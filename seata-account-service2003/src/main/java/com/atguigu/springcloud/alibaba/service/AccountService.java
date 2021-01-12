package com.atguigu.springcloud.alibaba.service;

import java.math.BigDecimal;

public interface AccountService {

    // 扣减账户余额
    public void decrease(Long userId, BigDecimal money);
}
