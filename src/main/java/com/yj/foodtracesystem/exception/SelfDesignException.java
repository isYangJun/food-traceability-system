package com.yj.foodtracesystem.exception;

import com.yj.foodtracesystem.controllerApi.ResultEnum;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 9:08 2018/8/1
 */
public class SelfDesignException extends RuntimeException {
    private Integer code;
    public SelfDesignException(ResultEnum resultEnum){
        super((resultEnum.getMsg()));
        this.code=resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
