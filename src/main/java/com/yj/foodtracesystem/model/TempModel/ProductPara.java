package com.yj.foodtracesystem.model.TempModel;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @Author:yangjun
 * @Description:
 * @Date: Created in 21:17 2018/6/3
 */
public class ProductPara {
    @NotNull
    public String proNum;
    @NotNull
    public String proName;

    @Override
    public String toString() {
        return "ProductPara{" +
                "proNum='" + proNum + '\'' +
                ", proName='" + proName + '\'' +
                ", age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NotNull
    @DecimalMax("10")
    public int age;

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
}
