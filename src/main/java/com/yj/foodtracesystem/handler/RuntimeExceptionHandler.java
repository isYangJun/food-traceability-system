package com.yj.foodtracesystem.handler;

import com.yj.foodtracesystem.controllerApi.Result;
import com.yj.foodtracesystem.controllerApi.ResultUtil;
import com.yj.foodtracesystem.exception.BaseException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 8:36 2018/8/1
 */
@ControllerAdvice
public class RuntimeExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return ResultUtil.error(baseException.getCode(), baseException.getMessage());
        }
        return ResultUtil.error(500, e.getMessage());
    }

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Result handle(BaseException e) {
            return ResultUtil.error(e.getCode(), e.getMessage());
    }

}
