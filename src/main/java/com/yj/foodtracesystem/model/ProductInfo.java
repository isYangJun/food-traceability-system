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
    private Date harvTime;

    @Column(name = "PI_COOPERATION_NUM")
    private int coopNum;
}
