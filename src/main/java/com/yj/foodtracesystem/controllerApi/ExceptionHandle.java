package com.yj.foodtracesystem.controllerApi;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 8:36 2018/8/1
 */
@ControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
     public Result handle(Exception e){
        if(e instanceof ExcepHandle){
            ExcepHandle excepHandle=(ExcepHandle)e;
            return ResultUtil.error(excepHandle.getCode(),excepHandle.getMessage());
        }
return ResultUtil.error(500,e.getMessage());
     }

}
