package com.atguigu.springcloud.commons;

public class RT {

    public static Result error(CM cm){
        return new Result(cm);
    }

    public static Result error(Integer code,String message){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static Result error(String message){
        Result result = new Result();
        result.setCode(Code.ERROR);
        result.setMessage(message);
        return result;
    }

    public static Result error(){
        Result result = new Result(CM.ERROR);
        return result;
    }

    public static Result success(){
        return new Result(CM.SUCCESS);
    }

    public static Result success(Integer code,String message){
        return new Result(code,message);
    }

    public static Result success(Integer code,String message,Object data){
        Result result = new Result(CM.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result success(Object data){
        Result result = new Result(CM.SUCCESS);
        result.setData(data);
        return result;
    }


}
