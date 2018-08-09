package com.yj.foodtracesystem.handler;

import com.yj.foodtracesystem.Result.Result;
import com.yj.foodtracesystem.Result.ResultUtil;
import com.yj.foodtracesystem.exception.BaseException;
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
public class RuntimeExceptionHandler{
    private static final Logger logger=LoggerFactory.getLogger(RuntimeExceptionHandler.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof BaseException) {
            logger.info("e instanceof BaseException:");
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
}
