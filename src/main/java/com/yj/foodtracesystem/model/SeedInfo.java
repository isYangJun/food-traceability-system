package com.yj.foodtracesystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 8:12 2018/5/3
 */
@Entity
@Table(name = "TBL_SEED_INFO")
public class SeedInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SI_ID")
    private int id;
    @Column(name = "SI_SEED_NAME")
    private String seedName;

    @Column(name = "SI_SEED_PRODUCER")
    @NotNull
    private String seedProducer;

    @Column(name = "SI_PRODUCE_PLACE")
    @NotNull
    private String seedRroPlace;

    @Column(name = "SI_PRODUCE_TIME")
    private String seedProTime;

    @Column(name = "SI_VALIDATE_TIME")
    @NotNull
    private String seedValTime;

    @Column(name = "SI_REGISTER_TIME")
    private String seedRegTime;

    @Column(name = "SI_SEED_WEIGHT")
    @NotNull
    private String seedWeight;

    @Column(name = "SI_OPERATOR_NUM")
    private String operatorNum;

    @Override
    public String toString() {
        return "SeedInfo{" +
                "id=" + id +
                ", seedName='" + seedName + '\'' +
                ", seedProducer='" + seedProducer + '\'' +
                ", seedRroPlace='" + seedRroPlace + '\'' +
                ", seedProTime='" + seedProTime + '\'' +
                ", seedValTime='" + seedValTime + '\'' +
                ", seedRegTime='" + seedRegTime + '\'' +
                ", seedWeight='" + seedWeight + '\'' +
                ", operatorNum='" + operatorNum + '\'' +
                '}';
    }

    public String getSeedRroPlace() {
        return seedRroPlace;
    }

    public void setSeedRroPlace(String seedRroPlace) {
        this.seedRroPlace = seedRroPlace;
    }

    public String getOperatorNum() {
        return operatorNum;
    }

    public void setOperatorNum(String operatorNum) {
        this.operatorNum = operatorNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public String getSeedProducer() { return seedProducer;}

    public void setSeedProducer(String seedProducer) { this.seedProducer = seedProducer; }

    public String getSeedProPlace() { return seedRroPlace;}

    public void setSeedProPlace(String seedRroPlace) { this.seedRroPlace = seedRroPlace; }

    public String getSeedProTime() { return seedProTime; }

    public void setSeedProTime(String seedProTime) { this.seedProTime = seedProTime; }

    public String getSeedValTime() {
        return seedValTime;
    }

    public void setSeedValTime(String seedValTime) {
        this.seedValTime = seedValTime;
    }

    public String getSeedRegTime() { return seedRegTime; }

    public void setSeedRegTime(String seedRegTime) { this.seedRegTime = seedRegTime; }

    public String getSeedWeight() { return seedWeight; }

    public void setSeedWeight(String seedWeight) { this.seedWeight = seedWeight; }
}