package com.yj.foodtracesystem.exception;

import com.yj.foodtracesystem.controllerApi.ResultEnum;

/**
 * 描述：
 * <p>
 *
 * @author: yangjun
 * @date: 2018/4/11 10:24
 */
public class TokenException extends BaseException {
    public TokenException(ResultEnum resultEnum) {
        super(resultEnum);
    }
}
