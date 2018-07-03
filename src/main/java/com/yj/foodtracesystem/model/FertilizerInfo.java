package com.yj.foodtracesystem.model;

import javax.persistence.*;
@Entity
@Table(name = "TBL_FERTILIZER_INFO")
public class FertilizerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FE_ID")
    private int id;
    @Column(name = "FE_FERTILIZER_NAME")
    private  String fertilizerName;
    @Column(name = "FE_FERTILIZER_type")
    private  String fertilizerType;
    @Column(name = "FE_FERTILIZER_PRODUCER")
    private  String fertilizerProducer;
    @Column(name = "FE_VALIDATE_TIME")
    private String fertilizerValTime;
    @Column(name = "FE_FERTILIZER_WEIGHT")
    private String fertilizerWeight;
    @Column(name = "FE_REGISTER_TIME")
    private  String fertilizerRegTime;
    @Column(name = "FE_OPERATOR_NUM")
    private String operatorNum;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFertilizerName() { return fertilizerName; }

    public void setFertilizerName(String fertilizerName) { this.fertilizerName = fertilizerName; }

    public String getFertilizerType() { return fertilizerType; }

    public void setFertilizerType(String fertilizerType) { this.fertilizerType = fertilizerType; }

    public String getFertilizerProducer() { return fertilizerProducer; }

    public void setFertilizerProducer(String fertilizerProducer) { this.fertilizerProducer = fertilizerProducer; }

    public String getFertilizerValTime() { return fertilizerValTime; }

    public void setFertilizerValTime(String fertilizerValTime) { this.fertilizerValTime = fertilizerValTime; }

    public String getFertilizerWeight() { return fertilizerWeight; }

    public void setFertilizerWeight(String fertilizerWeight) { this.fertilizerWeight = fertilizerWeight; }

    public String getFertilizerRegTime() { return fertilizerRegTime; }

    public void setFertilizerRegTime(String fertilizerRegTime) { this.fertilizerRegTime = fertilizerRegTime; }

    public String getOperatorNum() { return operatorNum; }

    public void setOperatorNum(String operatorNum) { this.operatorNum = operatorNum; }

    @Override
    public String toString() {
        return "FertilizerInfo{" +
                "id=" + id +
                ", fertilizerName='" + fertilizerName +
                ", fertilizerType='" + fertilizerType +
                ",fertilizerProducer='" + fertilizerProducer +
                ",fertilizerValTime='" + fertilizerValTime +
                ",fertilizerWeight='" + fertilizerWeight +
                ",fertilizerRegTime='" + fertilizerRegTime +
                ",operatorNum='" + operatorNum +
                '}';
    }
}

