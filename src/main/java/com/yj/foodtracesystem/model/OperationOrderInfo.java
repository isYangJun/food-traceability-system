package com.yj.foodtracesystem.model;

import javax.persistence.*;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 13:44 2018/7/15
 */
@Entity
@Table(name = "TBL_OPERATION_ORDER")
public class OperationOrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OO_ID")
    private int id;

    @Column(name = "OO_OPERATION_ID")
    private int operateTypeId;

    @Column(name = "OO_OPERATION_NAME")
    private String operationName;

    @Column(name = "OO_USER_ID")
    private String userId;

    @Column(name = "OO_USER_NAME")
    private String userName;

    @Column(name = "OO_CREATER_ID")
    private String createrId;
    @Column(name = "OO_CREATER_NAME")
    private String createrName;
    @Column(name = "OO_FILED_ID")
    private int filedId;

    @Column(name = "OO_FILED_NAME")
    private String filedName;

    @Column(name = "OO_ORDER_TIME")
    private String orderTime;

    @Column(name = "OO_SEED_ID")
    private int seedId;

    @Column(name = "OO_SEED_NAME")
    private String seedName;

    @Column(name = "OO_ISDONE")
    private int isDone;  //0未完成，1已完成

    @Override
    public String toString() {
        return "OperationOrderInfo{" +
                "id=" + id +
                ", operateTypeId=" + operateTypeId +
                ", operationName='" + operationName + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", createrId='" + createrId + '\'' +
                ", createrName='" + createrName + '\'' +
                ", filedId=" + filedId +
                ", filedName='" + filedName + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", seedId=" + seedId +
                ", seedName='" + seedName + '\'' +
                ", isDone=" + isDone +
                ", doneTime='" + doneTime + '\'' +
                '}';
    }

    public String getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(String doneTime) {
        this.doneTime = doneTime;
    }

    @Column(name = "OO_DOWN_TIME")
    private String doneTime;

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOperateTypeId() {
        return operateTypeId;
    }

    public void setOperateTypeId(int operateTypeId) {
        this.operateTypeId = operateTypeId;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getFiledId() {
        return filedId;
    }

    public void setFiledId(int filedId) {
        this.filedId = filedId;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getSeedId() {
        return seedId;
    }

    public void setSeedId(int seedId) {
        this.seedId = seedId;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }
}
