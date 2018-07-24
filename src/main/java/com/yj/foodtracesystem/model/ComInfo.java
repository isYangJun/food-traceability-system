package com.yj.foodtracesystem.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private String comNum;

    @Column(name = "CI_COMPANY_NAME")
    @NotNull
    private String comName;

    @Column(name = "CI_COMPANY_ROLE")
    @NotNull
    private int comRole;

    @Column(name = "CI_COMPANY_ROLE_INFO")
    @NotNull
    private String comRoleInfo;

    @Column(name = "CI_REGISTER_TIME")
    @NotNull
    private String comRegTime;

    @Column(name = "CI_COMPANY_ADD")
    @NotNull
    private String comAdd;

    @Override
    public String toString() {
        return "ComInfo{" +
                "id=" + id +
                ", comNum='" + comNum + '\'' +
                ", comName='" + comName + '\'' +
                ", comRole=" + comRole +
                ", comRoleInfo='" + comRoleInfo + '\'' +
                ", comRegTime='" + comRegTime + '\'' +
                ", comAdd='" + comAdd + '\'' +
                '}';
    }

    public String getComAdd() {
        return comAdd;
    }

    public void setComAdd(String comAdd) {
        this.comAdd = comAdd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getComRole() {
        return comRole;
    }

    public void setComRole(int comRole) {
        this.comRole = comRole;
    }

    public String getComRoleInfo() {
        return comRoleInfo;
    }

    public void setComRoleInfo(String comRoleInfo) {
        this.comRoleInfo = comRoleInfo;
    }

    public String getComRegTime() {
        return comRegTime;
    }

    public void setComRegTime(String comRegTime) {
        this.comRegTime = comRegTime;
    }
}
