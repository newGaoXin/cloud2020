package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.domain.Result;
import com.atguigu.springcloud.alibaba.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/account/decrease")
    public Result decrease(@RequestParam(value = "userId",required = true) Long userId,
                           @RequestParam(value = "money",required = true) BigDecimal money) {
        accountService.decrease(userId,money);

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Result(200,"扣减账户余额成功！");
    }
}
