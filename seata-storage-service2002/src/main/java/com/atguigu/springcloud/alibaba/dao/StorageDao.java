package com.atguigu.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageDao {

    // 扣减库存
    public void decrease(@Param("productId") Long productId, @Param("count") int count);

}
