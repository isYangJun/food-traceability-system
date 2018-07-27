package com.yj.foodtracesystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @NotNull
    private String filedRegTime;

    @Column(name = "FI_COMP_NUM")
    @NotNull
    private String filedCompNum;

    @Column(name = "PI_OPERATOR_NUM")
    @NotNull
    private String operatorNum;

    @Override
    public String toString() {
        return "FiledInfo{" +
                "id=" + id +
                ", filedArea=" + filedArea +
                ", filedName='" + filedName + '\'' +
                ", filedAdd='" + filedAdd + '\'' +
                ", filedRegTime='" + filedRegTime + '\'' +
                ", filedCompNum='" + filedCompNum + '\'' +
                ", operatorNum='" + operatorNum + '\'' +
                ", filedCompName='" + filedCompName + '\'' +
                '}';
    }

    public String getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String operatorNum) {
        this.operatorNum = operatorNum;
    }

    public String getFiledCompName() {
        return filedCompName;
    }

    public void setFiledCompName(String filedCompName) {
        this.filedCompName = filedCompName;
    }

    @Column(name = "FI_COMP_NAME")
    private String filedCompName;

    public String getFiledCompNum() {
        return filedCompNum;
    }

    public void setFiledCompNum(String filedCompNum) {
        this.filedCompNum = filedCompNum;
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

    public String getFiledRegTime() {
        return filedRegTime;
    }

    public void setFiledRegTime(String filedRegTime) {
        this.filedRegTime = filedRegTime;
    }
}
