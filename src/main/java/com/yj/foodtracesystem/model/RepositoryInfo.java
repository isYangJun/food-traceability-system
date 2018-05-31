package com.yj.foodtracesystem.model;

import javax.persistence.*;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 16:19 2018/5/30
 */
@Entity
@Table(name="TBL_REPOSITORY_INFO")
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

    @Column(name = "RI_PRODUCT_INRECORDED")
    private int inRecorded;

    @Column(name = "RI_RECORDED_TIME")
    private String recordedTime;

    @Column(name = "RI_SAVE_TEMP")
    private String saveTemp;
}
