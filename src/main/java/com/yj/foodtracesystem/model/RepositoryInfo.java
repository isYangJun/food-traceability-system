package com.yj.foodtracesystem.model;

import javax.persistence.*;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 16:19 2018/5/30
 */
@Entity
@Table(name = "TBL_REPOSITORY_INFO")
public class RepositoryInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RI_ID")
    private int id;

    @Column(name = "RI_WAREHOUSE_NUM")
    private String warehouseNum;

    @Column(name = "RI_WAREHOUSE_NAME")
    private String warehouseName;

    @Column(name = "RI_PRODUCT_NUM")
    private String proNum;

    @Column(name = "RI_PRODUCT_NAME")
    private String proName;

    public String getProBatchNum() {
        return proBatchNum;
    }

    public void setProBatchNum(String proBatchNum) {
        this.proBatchNum = proBatchNum;
    }

    @Column(name = "RI_PROBATCH_NUM")
    private String proBatchNum;


    @Column(name = "RI_PRODUCT_WEIGHT")
    private int proWeight;

    @Column(name = "RI_PRODUCT_INRECORDED")
    private int inRecorded;

    @Column(name = "RI_RECORDED_TIME")
    private String recordedTime;

    @Column(name = "RI_SAVE_TEMP")
    private String saveTemp;

    @Column(name = "RI_REPOSITORY_NUM")
    private String repositoryNum;//所属公司编号

    @Column(name = "RI_OPERATOR_NUM")
    private String operatorNum;

    public double getGrossLossRate() {
        return grossLossRate;
    }

    public void setGrossLossRate(double grossLossRate) {
        this.grossLossRate = grossLossRate;
    }

    @Override
    public String toString() {
        return "RepositoryInfo{" +
                "id=" + id +
                ", warehouseNum='" + warehouseNum + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", proNum='" + proNum + '\'' +
                ", proName='" + proName + '\'' +
                ", proBatchNum='" + proBatchNum + '\'' +
                ", proWeight=" + proWeight +
                ", inRecorded=" + inRecorded +
                ", recordedTime='" + recordedTime + '\'' +
                ", saveTemp='" + saveTemp + '\'' +
                ", repositoryNum='" + repositoryNum + '\'' +
                ", operatorNum='" + operatorNum + '\'' +
                ", grossLossRate=" + grossLossRate +
                '}';
    }

    @Column(name = "RI_GROSSLOSS_RATE")
    private double grossLossRate;

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

    public String getRepositoryNum() {
        return repositoryNum;
    }

    public void setRepositoryNum(String repositoryNum) {
        this.repositoryNum = repositoryNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWarehouseNum() {
        return warehouseNum;
    }

    public void setWarehouseNum(String warehouseNum) {
        this.warehouseNum = warehouseNum;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
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

    public String getSaveTemp() {
        return saveTemp;
    }

    public void setSaveTemp(String saveTemp) {
        this.saveTemp = saveTemp;
    }

}
