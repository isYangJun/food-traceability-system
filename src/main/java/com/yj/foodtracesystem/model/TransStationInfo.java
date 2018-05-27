package com.yj.foodtracesystem.model;

import javax.persistence.*;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 21:39 2018/5/25
 */
@Entity
@Table(name = "TBL_TRANSSTATION_INFO")
public class TransStationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TI_ID")
    private int id;
    @Column(name = "TI_CAR_NUM")
    private String carNum;
    @Column(name = "TI_CAR_NAME")
    private String carName;
    @Column(name = "TI_CAR_REG_TIME")
    private String carRegTime;
    @Column(name = "TI_COM_NUM")
    private String companyNum;
    @Column(name = "TI_COM_NAME")
    private String companyName;

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarRegTime() {
        return carRegTime;
    }

    public void setCarRegTime(String carRegTime) {
        this.carRegTime = carRegTime;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "TransStationInfo{" +
                "carNum='" + carNum + '\'' +
                ", carName='" + carName + '\'' +
                ", carRegTime='" + carRegTime + '\'' +
                ", companyNum='" + companyNum + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
