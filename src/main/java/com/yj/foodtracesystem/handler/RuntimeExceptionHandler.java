package com.yj.foodtracesystem.handler;

import com.yj.foodtracesystem.controllerApi.Result;
import com.yj.foodtracesystem.controllerApi.ResultEnum;
import com.yj.foodtracesystem.controllerApi.ResultUtil;
import com.yj.foodtracesystem.exception.BaseException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger=LoggerFactory.getLogger(RuntimeExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return ResultUtil.error(baseException.getCode(), baseException.getMessage());
        }
        return ResultUtil.error(500);
    }

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Result handle(BaseException e) {
        logger.info("RuntimeExceptionHandler-method:handle");
            return ResultUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = ExpiredJwtException.class)
    @ResponseBody
    public Result handle(ExpiredJwtException e){
        logger.info("token is expired");
        BaseException baseException=new BaseException(ResultEnum.EXPIRED_TOKEN);
        return ResultUtil.error(baseException.getCode(),baseException.getMessage());
    }

    @ExceptionHandler(value = UnsupportedJwtException.class)
    @ResponseBody
    public Result handle(UnsupportedJwtException e){
        logger.info("token is not supported");
        BaseException baseException=new BaseException(ResultEnum.UNSUPPORTED_TOKEN);
        return ResultUtil.error(baseException.getCode(),baseException.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public Result handle(RuntimeException e){
        logger.info("RuntimeException:token is not supported");
        BaseException baseException=new BaseException(ResultEnum.UNSUPPORTED_TOKEN);
        return ResultUtil.error(baseException.getCode(),baseException.getMessage());
    }
}
