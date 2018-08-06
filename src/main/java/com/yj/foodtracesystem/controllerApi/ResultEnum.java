package com.yj.foodtracesystem.controllerApi;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 9:22 2018/8/1
 */
public enum ResultEnum {
    UNKNOW_ERROR(-1,"error"),
    ARGUMENT_ERROR(1,"参数错误"),
    NULL_TOKEN(2,"token is null"),
    EXPIRED_TOKEN(3,"token is expired"),
    UNSUPPORTED_TOKEN(4,"token is unsupported"),
    UNFORMED_TOKEN(5,"token form is wrong"),
    SIGNFAIL_TOKEN(6,"sign failed"),
    ILLEAGUE_TOKEN(7,"illeague token para");


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
