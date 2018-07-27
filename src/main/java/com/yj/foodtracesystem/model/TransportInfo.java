package com.yj.foodtracesystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 20:59 2018/5/25
 */
@Entity
@Table(name = "TBL_TRANSPORT_INFO")
public class TransportInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TI_ID")
    private int id;

    @Column(name = "TI_COM_NUM")
    @NotNull
    private String comNum;

    @Column(name = "TI_COM_NAME")
    private String comName;

    @Column(name = "TI_PRODUCT_NUM")
    @NotNull
    private String proNum;

    @Column(name = "TI_PRODUCT_NAME")
    private String proName;

    @Column(name = "TI_PRODUCT_WEIGHT")
    @NotNull
    private int proWeight;

    @Column(name = "TI_PRODUCT_INRECORDED")
    private int inRecorded;

    @Column(name = "TI_RECORDED_TIME")
    private String recordedTime;

    @Column(name = "TI_DESTINATION_NUM")
    @NotNull
    private String destinationNum;

    @Column(name = "TI_DESTINATION_NAME")
    @NotNull
    private String destinationName;

    public double getGrossLossRate() {
        return grossLossRate;
    }

    public void setGrossLossRate(double grossLossRate) {
        this.grossLossRate = grossLossRate;
    }

    @Override
    public String toString() {
        return "TransportInfo{" +
                "id=" + id +
                ", comNum='" + comNum + '\'' +
                ", comName='" + comName + '\'' +
                ", proNum='" + proNum + '\'' +
                ", proName='" + proName + '\'' +
                ", proWeight=" + proWeight +
                ", inRecorded=" + inRecorded +
                ", recordedTime='" + recordedTime + '\'' +
                ", destinationNum='" + destinationNum + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", grossLossRate=" + grossLossRate +
                ", destinationRole=" + destinationRole +
                ", carNum='" + carNum + '\'' +
                ", operatorNum='" + operatorNum + '\'' +
                ", proBatchNum='" + proBatchNum + '\'' +
                '}';
    }

    @Column(name = "TI_GROSSLOSS_RATE")
    private double grossLossRate;

    public int getDestinationRole() {
        return destinationRole;
    }

    public void setDestinationRole(int destinationRole) {
        this.destinationRole = destinationRole;
    }

    @Column(name = "TI_DESTINATION_ROLE")
    private int destinationRole;

    @Column(name = "TI_CAR_NUM")
    private String carNum;

    @Column(name = "TI_OPERATOR_NUM")
    private String operatorNum;

    public String getProBatchNum() {
        return proBatchNum;
    }

    public void setProBatchNum(String proBatchNum) {
        this.proBatchNum = proBatchNum;
    }

    @Column(name = "TI_PROBATCH_NUM")
    private String proBatchNum;

    public int getProWeight() {
        return proWeight;
    }

    public void setProWeight(int proWeight) {
        this.proWeight = proWeight;
    }

    public String getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String operatorNum) {
        this.operatorNum = operatorNum;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getDestinationNum() {
        return destinationNum;
    }

    public void setDestinationNum(String destinationNum) {
        this.destinationNum = destinationNum;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getComNum() {
        return comNum;
    }

    public void setComNum(String comNum) {
        this.comNum = comNum;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public int getInRecorded() {
        return inRecorded;
    }

    public void setInRecorded(int inRecorded) {
        this.inRecorded = inRecorded;
    }

    public String getRecordedTime() {
        return recordedTime;
    }

    public void setRecordedTime(String recordedTime) {
        this.recordedTime = recordedTime;
    }

}
