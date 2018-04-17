package com.yj.foodtraceabilitytystem.dao;

import javax.persistence.*;

/**
 * @Author:YangJun
 * @Description:
 * @Date: Created in 8:24 2018/4/16
 */
@Entity
@Table(name="TBL_USER_TYPE")//设置数据库中表名字
public class UserType {

    @Column(name="UT_USER_ROLE_INFO")//设置数据库中字段名字，也可以设置长度，是否为空等属性
    private String userRoleInfo;
    @Id
    @Column(name="UT_USER_ROLE_ID")
    private int userRoleId;

    public UserType(String userRoleInfo, int userRoleId) {
        this.userRoleInfo = userRoleInfo;
        this.userRoleId = userRoleId;
    }

    public UserType() {
    }

    public String getUserRoleInfo() {
        return userRoleInfo;
    }

    public void setUserRoleInfo(String userRoleInfo) {
        this.userRoleInfo = userRoleInfo;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public String toString() {
        return "UserType{" +
                "userRoleInfo='" + userRoleInfo + '\'' +
                ", userRoleId=" + userRoleId +
                '}';
    }
}
