package com.yj.foodtracesystem.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 6:27 2018/5/4
 */
@Entity
@Table(name = "TBL_COMPANY_INFO")
public class ComInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CI_ID")
    private int id;

    @Column(name = "CI_COMPANY_NUM")
    private String comNum;

    @Column(name = "CI_COMPANY_NAME")
    private String comName;

    @Column(name = "CI_COMPANY_ROLE")
    private int comRole;

    @Column(name = "CI_COMPANY_ROLE_INFO")
    private String comRoleInfo;

    @Column(name = "CI_REGISTER_TIME")
    private Date comRegTime;
}
