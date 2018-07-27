package com.yj.foodtracesystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_PRODUCT_BATCHINFO")
public class ProductBatchInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PB_ID")
    private int id;

    @Column(name = "PB_FILED_NUM")
    @NotNull
    private int filedNum;

    @Column(name = "PB_SEED_NUM")
    @NotNull
    private int seedNum;

    @Column(name = "PB_OPERATOR_NUM")
    @NotNull
    private String operatorNum;

    @Column(name = "PB_PROBATCH_NUM")
    @NotNull
    private String proBatchNum;

    @Column(name = "PB_SEED_NAME")
    private String seedName;

    @Column(name = "PB_HARVEST_TIME")
    @NotNull
    private String harvTime;

    @Column(name = "PB_COOPERATION_NUM")
    @NotNull
    private String coopNum;

    @Column(name = "PB_PRODUCT_WEIGHT")
    @NotNull
    private int proWeight;

    @Column(name = "PB_COOPERATION_NAME")
    private String coopName;

    @Override
    public String toString() {
        return "ProductBatchInfo{" +
                "id=" + id +
                ", filedNum=" + filedNum +
                ", seedNum=" + seedNum +
                ", operatorNum='" + operatorNum + '\'' +
                ", proBatchNum='" + proBatchNum + '\'' +
                ", seedName='" + seedName + '\'' +
                ", harvTime='" + harvTime + '\'' +
                ", coopNum='" + coopNum + '\'' +
                ", proWeight='" + proWeight + '\'' +
                ", coopName='" + coopName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFiledNum() {
        return filedNum;
    }

    public void setFiledNum(int filedNum) {
        this.filedNum = filedNum;
    }

    public int getSeedNum() {
        return seedNum;
    }

    public void setSeedNum(int seedNum) {
        this.seedNum = seedNum;
    }

    public String getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String operatorNum) {
        this.operatorNum = operatorNum;
    }

    public String getProBatchNum() {
        return proBatchNum;
    }

    public void setProBatchNum(String proBatchNum) {
        this.proBatchNum = proBatchNum;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public String getHarvTime() {
        return harvTime;
    }

    public void setHarvTime(String harvTime) {
        this.harvTime = harvTime;
    }

    public String getCoopNum() {
        return coopNum;
    }

    public void setCoopNum(String coopNum) {
        this.coopNum = coopNum;
    }

    public int getProWeight() {
        return proWeight;
    }

    public void setProWeight(int proWeight) {
        this.proWeight = proWeight;
    }

    public String getCoopName() {
        return coopName;
    }

    public void setCoopName(String coopName) {
        this.coopName = coopName;
    }
}
