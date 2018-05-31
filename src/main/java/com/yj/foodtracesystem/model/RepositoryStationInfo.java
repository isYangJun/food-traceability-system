package com.yj.foodtracesystem.model;

import javax.persistence.*;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 16:19 2018/5/30
 */
@Entity
@Table(name = "TBL_REPOSITORY_STATION_INFO")
public class RepositoryStationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RSI_ID")
    private int id;

    @Column(name = "RSI_REPOSITORY_NUM")
    private String repositoryNum;

    @Column(name = "RSI_REPOSITORY_NAME")
    private String repositoryName;

    @Column(name = "RSI_WAREHOUSE_NUM")
    private String warehouseNum;

    @Column(name = "RSI_WAREHOUSE_NAME")
    private String warehouseName;

    @Column(name = "RSI_WAREHOUSE_SETUP_TIME")
    private String warehouseSetUpTime;

    @Column(name = "RSI_SAVE_TEMP")
    private String saveTemp;

    @Column(name = "RSI_SAVE_TIME")
    private String saveTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepositoryNum() {
        return repositoryNum;
    }

    public void setRepositoryNum(String repositoryNum) {
        this.repositoryNum = repositoryNum;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
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

    public String getWarehouseSetUpTime() {
        return warehouseSetUpTime;
    }

    public void setWarehouseSetUpTime(String warehouseSetUpTime) {
        this.warehouseSetUpTime = warehouseSetUpTime;
    }

    public String getSaveTemp() {
        return saveTemp;
    }

    public void setSaveTemp(String saveTemp) {
        this.saveTemp = saveTemp;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    @Override
    public String toString() {
        return "RepositoryStationInfo{" +
                "id=" + id +
                ", repositoryNum='" + repositoryNum + '\'' +
                ", repositoryName='" + repositoryName + '\'' +
                ", warehouseNum='" + warehouseNum + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseSetUpTime='" + warehouseSetUpTime + '\'' +
                ", saveTemp='" + saveTemp + '\'' +
                ", saveTime='" + saveTime + '\'' +
                '}';
    }
}
