package com.yj.foodtracesystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 22:50 2018/4/18
 */
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_num")
    @NotEmpty(message = "*Please provide an user num")
    private String userNum;

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    @org.springframework.data.annotation.Transient
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "idNum")
    private String idNum;

    @Column(name = "regTime")
    private String regTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userNum='" + userNum + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", idNum='" + idNum + '\'' +
                ", regTime='" + regTime + '\'' +
                ", userRole=" + userRole +
                ", userComp='" + userComp + '\'' +
                ", userCompName='" + userCompName + '\'' +
                ", active=" + active +
                ", roles=" + roles +
                '}';
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    @Column(name="userRole")
    private int userRole;

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getUserCompName() {
        return userCompName;
    }

    public void setUserCompName(String userCompName) {
        this.userCompName = userCompName;
    }

    public String getUserComp() {
        return userComp;
    }

    public void setUserComp(String userComp) {
        this.userComp = userComp;
    }

    @Column(name = "user_comp")
    private String userComp;

    @Column(name = "user_comp_name")
    private String userCompName;

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
