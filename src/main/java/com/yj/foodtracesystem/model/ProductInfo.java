package com.yj.foodtracesystem.model;

import javax.persistence.*;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 6:14 2018/5/4
 */
@Entity
@Table(name = "TBL_PRODUCT_INFO")
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PI_ID")
    private int id;

    @Column(name = "PI_PRODUCT_NUM")
    private String proNum;

    @Column(name = "PI_PRODUCT_NAME")
    private String proName;

    @Column(name = "PI_FILED_NUM")
    private int filedNum;

    @Column(name = "PI_SEED_NUM")
    private int seedNum;

    @Column(name = "PI_IS_TRANSED")
    private int isTransed;//0为未操作，1为操作完成

    @Column(name = "PI_OPERATOR_NUM")
    private String operatorNum;

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id +
                ", proNum='" + proNum + '\'' +
                ", proName='" + proName + '\'' +
                ", filedNum=" + filedNum +
                ", seedNum=" + seedNum +
                ", isTransed=" + isTransed +
                ", operatorNum='" + operatorNum + '\'' +
                ", seedName='" + seedName + '\'' +
                ", harvTime='" + harvTime + '\'' +
                ", coopNum='" + coopNum + '\'' +
                ", coopName='" + coopName + '\'' +
                '}';
    }

    public String getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String operatorNum) {
        this.operatorNum = operatorNum;
    }

    public int getIsTransed() {
        return isTransed;
    }

    public void setIsTransed(int isTransed) {
        this.isTransed = isTransed;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    @Column(name = "PI_SEED_NAME")
    private String seedName;

    @Column(name = "PI_HARVEST_TIME")
    private String harvTime;

    @Column(name = "PI_COOPERATION_NUM")
    private String coopNum;

    public String getCoopName() {
        return coopName;
    }

    public void setCoopName(String coopName) {
        this.coopName = coopName;
    }

    @Column(name = "PI_COOPERATION_NAME")
    private String coopName;

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

}
