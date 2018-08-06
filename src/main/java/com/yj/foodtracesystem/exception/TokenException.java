package com.yj.foodtracesystem.exception;

import com.yj.foodtracesystem.controllerApi.ResultEnum;

/**
 * 描述：
 * <p>
 *
 * @author: yangjun
 * @date: 2018/4/11 10:24
 */
public class TokenException extends RuntimeException {
    private Integer code;
    public TokenException(ResultEnum resultEnum){
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
