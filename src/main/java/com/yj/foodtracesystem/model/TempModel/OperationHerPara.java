package com.yj.foodtracesystem.model.TempModel;

public class OperationHerPara {
    public String startTime;
    public String endTime;
    public int seedId;
    public int fertilizerId;
    public int pesticideId;

    @Override
    public String toString() {
        return "OperationHerPara{" +
                "startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ",seedId='" +seedId+ '\''+
                ",fertilizerId='" +fertilizerId +'\''+
                ",pesticideId='" + pesticideId +'\'' +
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

    public int getFertilizerId() { return fertilizerId; }

    public void setFertilizerId(int fertilizerId) { this.fertilizerId = fertilizerId; }

    public int getPesticideId() { return pesticideId; }

    public void setPesticideId(int pesticideId) { this.pesticideId = pesticideId; }
}
