package com.yj.foodtracesystem.model;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "PI_HARVEST_TIME")
    private String harvTime;

    @Column(name = "PI_COOPERATION_NUM")
    private int coopNum;

    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id +
                ", proNum='" + proNum + '\'' +
                ", proName='" + proName + '\'' +
                ", filedNum=" + filedNum +
                ", seedNum=" + seedNum +
                ", harvTime=" + harvTime +
                ", coopNum=" + coopNum +
                '}';
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

    public int getCoopNum() {
        return coopNum;
    }

    public void setCoopNum(int coopNum) {
        this.coopNum = coopNum;
    }
}
