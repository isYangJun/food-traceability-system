package com.yj.foodtracesystem.model.TempModel;

public class OperationHerPara {
    public String startTime;
    public String endTime;
    public int seedId;

    @Override
    public String toString() {
        return "OperationHerPara{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ",seedId='" +seedId+ '\''+
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

    public int getSeedId(){return seedId;}

    public void setSeedId(int seedId){this.seedId=seedId;}
}
