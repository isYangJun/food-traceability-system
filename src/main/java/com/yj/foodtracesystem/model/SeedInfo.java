package com.yj.foodtracesystem.model;

import javax.persistence.*;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 8:12 2018/5/3
 */
@Entity
@Table(name = "TBL_SEED_INFO")
public class SeedInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SI_ID")
    private int id;
    @Column(name = "SI_SEED_NAME")
    private String seedName;
    @Column(name = "SI_VALIDATE_TIME")
    private String validateTime;

}
