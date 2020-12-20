package com.atguigu.springcloud.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer code;

    private String message;

    private Object data;

    public Result(Integer code,String message){
        this(code,message,null);
    }

    public Result(CM cm){
        this(cm.getCode(),cm.getMessage(),null);
    }

}
