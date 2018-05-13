package com.yj.foodtracesystem.model.TempModel;

import java.util.Date;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 15:26 2018/5/7
 */
public class OperationHisPara {
    public String startTime;
    public String endTime;
    public int filedId;

    @Override
    public String toString() {
        return "OperationHisPara{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", filedId=" + filedId +
                '}';
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getFiledId() {
        return filedId;
    }

    public void setFiledId(int filedId) {
        this.filedId = filedId;
    }
}
