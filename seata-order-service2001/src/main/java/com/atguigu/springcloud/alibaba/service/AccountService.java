package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.alibaba.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient("seata-account-service")
public interface AccountService {

    @PostMapping("/account/decrease")
    public Result decrease(@RequestParam(value = "userId", required = true) Long userId,
                           @RequestParam(value = "money", required = true) BigDecimal money);

}
