package com.yj.foodtracesystem.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 7:17 2018/5/3
 */
@Entity
@Table(name = "TBL_FILED_OPERATION")
public class FiledOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FO_ID")
    private int id;

    @Column(name = "FO_OPERATION_ID")
    private int operateTypeId;

    public String getOperationName() {
        return operationName;
    }

    @Override
    public String toString() {
        return "FiledOperation{" +
                "id=" + id +
                ", operateTypeId=" + operateTypeId +
                ", operationName='" + operationName + '\'' +
                ", userId=" + userId +
                ", filedId=" + filedId +
                ", filedName='" + filedName + '\'' +
                ", operateTime=" + operateTime +
                ", seedId=" + seedId +
                ", seedName='" + seedName + '\'' +
                ", operationMemo='" + operationMemo + '\'' +
                '}';
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    @Column(name = "FO_OPERATION_NAME")
    private String operationName;

    @Column(name = "FO_USER_ID")
    private int userId;

    @Column(name = "FO_FILED_ID")
    private int filedId;

    @Column(name = "FO_FILED_NAME")
    private String filedName;

    @Column(name = "FO_OPERATION_TIME")
    private Date operateTime;

    @Column(name = "FO_SEED_ID")
    private int seedId;

    @Column(name = "FO_SEED_NAME")
    private String seedName;

    @Column(name = "FO_OPERATION_MEMO")
    private String operationMemo;

    public String getOperationMemo() {
        return operationMemo;
    }

    public void setOperationMemo(String operationMemo) {
        this.operationMemo = operationMemo;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFiledId() {
        return filedId;
    }

    public void setFiledId(int filedId) {
        this.filedId = filedId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
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

}
