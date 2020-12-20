package com.atguigu.springcloud.commons;


public enum CM {

    SUCCESS(200,"success"),
    ERROR(404,"error");

    private int code;

    private String message;

    private CM(int code,String message){
     this.code = code;
     this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
