package com.yj.foodtracesystem.model.TempModel;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 23:39 2018/5/12
 */
public class OperationAddPara {
    public int fiId;
    public int operaTypeId;
    public String memo;
    public int seedId;

    @Override
    public String toString() {
        return "OperationAddPara{" +
                "filedId=" + fiId +
                ", operaTypeId=" + operaTypeId +
                ", memo='" + memo + '\'' +
                '}';
    }

    public int getFiledId() {
        return fiId;
    }

    public void setFiledId(int filedId) {
        this.fiId = filedId;
    }

    public int getOperaTypeId() {
        return operaTypeId;
    }

    public void setOperaTypeId(int operaTypeId) {
        this.operaTypeId = operaTypeId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
