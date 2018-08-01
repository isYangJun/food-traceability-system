package com.yj.foodtracesystem.controllerApi;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 9:08 2018/8/1
 */
public class ExcepHandle extends RuntimeException {
    private Integer code;
    public ExcepHandle(ResultEnum resultEnum){
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
