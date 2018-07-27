package com.yj.foodtracesystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private String carNum;

    @Column(name = "TI_CAR_NAME")
    private String carName;

    @Column(name = "TI_CAR_TYPE")
    @NotNull
    private String carType;

    @Column(name = "TI_CAR_REG_TIME")
    private String carRegTime;

    @Column(name = "TI_COM_NUM")
    @NotNull
    private String companyNum;

    @Column(name = "TI_COM_NAME")
    private String companyName;

    @Column(name = "TI_OPER_NUM")
    @NotNull
    private String operatorNum;


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

    public String getCarType() { return carType; }

    public void setCarType(String carType) { this.carType = carType; }

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

    public String  getOperatorNum(){return operatorNum;}

    public void setOperatorNum(String operatorNum) { this.operatorNum = operatorNum; }



    @Override
    public String toString() {
        return "TransStationInfo{" +
                "id='" + id + '\'' +
                "carNum='" + carNum + '\'' +
                ", carName='" + carName + '\'' +
                ", carType='" + carType + '\'' +
                ", carRegTime='" + carRegTime + '\'' +
                ", companyNum='" + companyNum + '\'' +
                ", companyName='" + companyName + '\'' +
                ", operatorNum='" +operatorNum+'\''+
                '}';
    }
}
