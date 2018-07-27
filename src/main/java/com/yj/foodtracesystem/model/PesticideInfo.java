package com.yj.foodtracesystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_PESTICIDE_INFO")
public class PesticideInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PE_ID")
    private int id;

    @Column(name = "PE_PESTICIDE_NAME")
    private  String pesticideName;

    @Column(name = "PE_PESTICIDE_TYPE")
    @NotNull
    private  String pesticideType;

    @Column(name = "PE_PESTICIDE_PRODUCER")
    @NotNull
    private String pesticideProducer;

    @Column(name = "PE_VALIDATE_TIME")
    @NotNull
    private  String pesticideValTime;

    @Column(name = "PE_PESTICIDE_WEIGHT")
    @NotNull
    private String pesticideWeight;

    @Column(name = "PE_REGISTER_TIME")
    @NotNull
    private  String pesticideRegTime;

    @Column(name = "PE_OPERATOR_NUM")
    @NotNull
    private  String operatorNum;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getPesticideName() { return pesticideName; }

    public void setPesticideName(String pesticideName) { this.pesticideName = pesticideName; }

    public String getPesticideType() { return pesticideType; }

    public void setPesticideType(String pesticideType) { this.pesticideType = pesticideType; }

    public String getPesticideProducer() { return pesticideProducer; }

    public void setPesticideProducer(String pesticideProducer) { this.pesticideProducer = pesticideProducer; }

    public String getPesticideValTime() { return pesticideValTime; }

    public void setPesticideValTime(String pesticideValTime) { this.pesticideValTime = pesticideValTime; }

    public String getPesticideWeight() { return pesticideWeight; }

    public void setPesticideWeight(String pesticideWeight) { this.pesticideWeight = pesticideWeight; }

    public String getPesticideRegTime() { return pesticideRegTime; }

    public void setPesticideRegTime(String pesticideRegTime) { this.pesticideRegTime = pesticideRegTime; }

    public String getOperatorNum() { return operatorNum; }

    public void setOperatorNum(String operatorNum) { this.operatorNum = operatorNum; }

    @Override
    public String toString() {
        return "PesticideInfo{" +
                "id=" + id +
                ", pesticideName='" + pesticideName +
                ", pesticideType='" + pesticideType +
                ",pesticideProducer='" + pesticideProducer +
                ",pesticideValTime='" + pesticideValTime +
                ",pesticideWeight='" + pesticideWeight +
                ",pesticideRegTime='" + pesticideRegTime +
                ",operatorNum='" + operatorNum +
                '}';
    }
}
