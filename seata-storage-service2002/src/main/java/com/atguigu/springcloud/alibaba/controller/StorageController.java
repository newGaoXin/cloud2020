package com.atguigu.springcloud.alibaba.controller;

import com.atguigu.springcloud.alibaba.domain.Result;
import com.atguigu.springcloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("storage/decrease")
    public Result decrease(@RequestParam(value = "productId", required = true) Long productId,
                           @RequestParam(value = "count", required = true) int count) {
        storageService.decrease(productId, count);
        return new Result(200, "扣减库存成功！");
    }
}
