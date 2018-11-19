package com.yj.foodtracesystem.model;

import javax.persistence.*;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 17:09 2018/11/19
 */
@Entity
@Table(name = "TBL_WAREHOUSE_INFO")
public class WarehouseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WI_ID")
    private int id;

    @Column(name = "WI_COOPERATION_NUM")
    private String coopNum;

    @Column(name = "WI_WAREHOUSE_NUM")
    private String warehouseNum;

    @Column(name = "WI_WAREHOUSADDE_NAME")
    private String warehouseName;

    public String getWarehouseAdd() {
        return warehouseAdd;
    }

    public void setWarehouseAdd(String warehouseAdd) {
        this.warehouseAdd = warehouseAdd;
    }

    @Column(name = "WI_WAREHOUSE_ADD")
    private String warehouseAdd;

    @Override
    public String toString() {
        return "WarehouseInfo{" +
                "id=" + id +
                ", coopNum='" + coopNum + '\'' +
                ", warehouseNum='" + warehouseNum + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", warehouseAdd='" + warehouseAdd + '\'' +
                ", wareHouseTemp=" + wareHouseTemp +
                ", regTime='" + regTime + '\'' +
                '}';
    }

    @Column(name = "WI_WAREHOUSE_TEMP")
    private int wareHouseTemp;


    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    @Column(name = "WI_WAREHOUSE_TIME")
    private String regTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoopNum() {
        return coopNum;
    }

    public void setCoopNum(String coopNum) {
        this.coopNum = coopNum;
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

    public int getWareHouseTemp() {
        return wareHouseTemp;
    }

    public void setWareHouseTemp(int wareHouseTemp) {
        this.wareHouseTemp = wareHouseTemp;
    }
}
