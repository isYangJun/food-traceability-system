package com.yj.foodtracesystem.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 8:05 2018/5/3
 */
@Entity
@Table(name = "TBL_FILED_INFO")
public class FiledInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FI_ID")
    private int id;
    @Column(name = "FI_FILED_AREA")
    private int filedArea;
    @Column(name = "FI_FILED_NAME")
    private String filedName;
    @Column(name = "FI_FILED_ADD")
    private String filedAdd;
    @Column(name = "FI_REGISTER_TIME")
    private Date filedRegTime;

    @Override
    public String toString() {
        return "FiledInfo{" +
                "id=" + id +
                ", filedArea=" + filedArea +
                ", filedName='" + filedName + '\'' +
                ", filedAdd='" + filedAdd + '\'' +
                ", filedRegTime=" + filedRegTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFiledArea() {
        return filedArea;
    }

    public void setFiledArea(int filedArea) {
        this.filedArea = filedArea;
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getFiledAdd() {
        return filedAdd;
    }

    public void setFiledAdd(String filedAdd) {
        this.filedAdd = filedAdd;
    }

    public Date getFiledRegTime() {
        return filedRegTime;
    }

    public void setFiledRegTime(Date filedRegTime) {
        this.filedRegTime = filedRegTime;
    }
}
