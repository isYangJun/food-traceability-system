package com.yj.foodtracesystem.model.TempModel;

import java.util.Date;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 14:39 2018/5/7
 */

public class OperationHisResult {

    public String filedId;


    public String filedName;


    public String userNum;


    public String userName;


    public String operaInfo;


    public String seedName;


    public String operationTime;


    public String memo;

    @Override
    public String toString() {
        return "OperationHisResult{" +
                "filedId=" + filedId +
                ", filedName='" + filedName + '\'' +
                ", userNum='" + userNum + '\'' +
                ", userName='" + userName + '\'' +
                ", operaInfo='" + operaInfo + '\'' +
                ", seedName='" + seedName + '\'' +
                ", operationTime=" + operationTime +
                ", memo='" + memo + '\'' +
                '}';
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }


    public String  getFiledId() {
        return filedId;
    }

    public void setFiledId(String filedId) {
        this.filedId = filedId;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperaInfo() {
        return operaInfo;
    }

    public void setOperaInfo(String operaInfo) {
        this.operaInfo = operaInfo;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
