package com.yj.foodtracesystem.model;

import javax.persistence.*;

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
    private String comNum;

    @Column(name = "TI_COM_NAME")
    private String comName;

    @Column(name = "TI_PRODUCT_NUM")
    private String proNum;

    @Column(name = "TI_PRODUCT_NAME")
    private String proName;

    @Column(name = "TI_PRODUCT_INRECORDED")
    private int inRecorded;

    @Column(name = "TI_RECORDED_TIME")
    private String recordedTime;

    @Column(name = "TI_DESTINATION_NUM")
    private String destinationNum;

    @Column(name = "TI_DESTINATION_NAME")
    private String destinationName;

    @Column(name = "TI_CAR_NUM")
    private String carNum;


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

    @Override
    public String toString() {
        return "TransportInfo{" +
                "id=" + id +
                ", comNum='" + comNum + '\'' +
                ", comName='" + comName + '\'' +
                ", proNum='" + proNum + '\'' +
                ", proName='" + proName + '\'' +
                ", inRecorded=" + inRecorded +
                ", recordedTime='" + recordedTime + '\'' +
                ", destinationNum='" + destinationNum + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", carNum='" + carNum + '\'' +
                '}';
    }
}
