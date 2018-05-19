package com.yj.foodtracesystem.model.TempModel;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 21:27 2018/5/16
 */
public class QueryPara {
    public String startTime;

    @Override
    public String toString() {
        return "QueryPara{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
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

    public String endTime;
}
