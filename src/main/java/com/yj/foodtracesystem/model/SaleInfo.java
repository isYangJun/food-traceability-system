package com.yj.foodtracesystem.model;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 19:22 2018/6/3
 */
@Entity
@Table(name = "TBL_SALE_INFO")
public class SaleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SI_ID")
    private int id;
    @Column(name = "SI_PRO_NUM")
    private String proNum;

    public String getProBatchNum() {
        return proBatchNum;
    }

    public void setProBatchNum(String proBatchNum) {
        this.proBatchNum = proBatchNum;
    }

    @Column(name = "SI_PRO_NAME")
    private String proName;

    @Column(name = "SI_PROBATCH_NUM")
    @NotNull
    private String proBatchNum;

    @Override
    public String toString() {
        return "SaleInfo{" +
                "id=" + id +
                ", proNum='" + proNum + '\'' +
                ", proName='" + proName + '\'' +
                ", proBatchNum='" + proBatchNum + '\'' +
                ", grossLossRate=" + grossLossRate +
                ", proWeight=" + proWeight +
                ", recordedTime='" + recordedTime + '\'' +
                ", comNum='" + comNum + '\'' +
                ", comName='" + comName + '\'' +
                ", inRecorded=" + inRecorded +
                ", operatorNum='" + operatorNum + '\'' +
                '}';
    }

    public double getGrossLossRate() {
        return grossLossRate;
    }

    public void setGrossLossRate(double grossLossRate) {
        this.grossLossRate = grossLossRate;
    }

    @Column(name = "SI_GROSSLOSS_RATE")
    private double grossLossRate;



    public int getProWeight() {
        return proWeight;
    }

    public void setProWeight(int proWeight) {
        this.proWeight = proWeight;
    }

    @Column(name = "SI_PRO_WEIGHT")
    private int proWeight;

    @Column(name = "SI_RECORDED_TIME")
    private String recordedTime;
    @Column(name = "SI_COM_NUM")
    private String comNum;
    @Column(name = "SI_COM_NAME")
    private String comName;
    @Column(name = "SI_PRODUCT_INRECORDED")
    private int inRecorded;
    @Column(name = "SI_OPERATOR_NUM")
    private String operatorNum;

    public String getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String operatorNum) {
        this.operatorNum = operatorNum;
    }

    public int getInRecorded() {
        return inRecorded;
    }

    public void setInRecorded(int inRecorded) {
        this.inRecorded = inRecorded;
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

    public String getRecordedTime() {
        return recordedTime;
    }

    public void setRecordedTime(String recordedTime) {
        this.recordedTime = recordedTime;
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

}
