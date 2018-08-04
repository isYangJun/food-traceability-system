package com.yj.foodtracesystem.controllerApi;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 9:22 2018/8/1
 */
public enum ResultEnum {
    UNKNOW_ERROR(-1,"error"),
    KNOW_EXCEPTION(0,"df");

    private Integer code;
    private String msg;

     ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
