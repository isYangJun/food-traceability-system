package com.yj.foodtraceabilitytystem.dao;

import javax.persistence.*;

/**
 * @Author:YangJun
 * @Description: userinfo for login
 * @Date: Created in 22:16 2018/4/15
 */
@Entity
@Table(name="TBL_USER_INFO")//设置数据库中表名字
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="UI_USER_ID")
    private int id;

    @Column(name="UI_USER_NAME")//设置数据库中字段名字，也可以设置长度，是否为空等属性
    private String username;

    @Column(name="UI_USER_NUM")
    private int userNum;

    @Column(name="UI_USER_PASS")
    private String password;

    @Column(name="UI_USER_ROLE_ID")
    private int userRoleId;

    public User() {
    }

    public User(String username, int userNum, String password, int userRoleId) {
        this.username = username;
        this.userNum = userNum;
        this.password = password;
        this.userRoleId = userRoleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userNum=" + userNum +
                ", password='" + password + '\'' +
                ", userRoleId=" + userRoleId +
                '}';
    }
}
