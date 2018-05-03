package com.yj.foodtracesystem.model;

import javax.persistence.*;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 7:41 2018/5/3
 */
@Entity
@Table(name = "TBL_OPERATION_TYPE")
public class FiledOperationType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OT_OPERATION_ID")
    private int id;

    @Column(name = "OT_OPERATION_NAME")
    private String operationName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    @Override
    public String toString() {
        return "FiledOperationType{" +
                "id=" + id +
                ", operationName='" + operationName + '\'' +
                '}';
    }
}
