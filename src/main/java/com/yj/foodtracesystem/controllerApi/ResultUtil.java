package com.yj.foodtracesystem.controllerApi;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 8:43 2018/8/1
 */
public class ResultUtil {
    public static Result success(Object object){
        Result result=new Result();
        result.setCode(200);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static Result success(){
        Result result=new Result();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }

    public static Result error(Integer code, String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    public static Result error(Integer code){
        Result result=new Result();
        result.setCode(code);
        return result;
    }
}
