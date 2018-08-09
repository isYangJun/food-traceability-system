package com.yj.foodtracesystem.Result;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 8:11 2018/8/1
 */
public class Result<T> {
    private Integer code;
    private String msg;
    private  T data;

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
