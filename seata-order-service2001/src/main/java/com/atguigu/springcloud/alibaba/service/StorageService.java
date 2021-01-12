package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.alibaba.domain.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("seata-storage-service")
public interface StorageService {

    @PostMapping("storage/decrease")
    public Result decrease(@RequestParam(value = "productId", required = true) Long productId,
                           @RequestParam(value = "count", required = true) int count);
}
