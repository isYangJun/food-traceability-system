package com.yj.foodtracesystem.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TBL_CAR")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
    private int id;

    @Column(name = "car_type")
    @NotNull
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() { return type; }

    public void setType(String type ) { this.type = type; }
    @Override
    public String toString(){
        return "Car{" +
                "id" + id +
                ",type='" + type +'\'' +
                '}';
    }
}
