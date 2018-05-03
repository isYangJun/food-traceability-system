package com.yj.foodtracesystem.model;

import javax.persistence.*;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 6:45 2018/5/4
 */
@Entity
@Table(name = "TBL_COMPANY_ROLE")
public class ComRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CR_ROLE_ID")
    private int id;

    @Column(name = "CR_ROLE_NAME")
    private String comRoleName;
}
