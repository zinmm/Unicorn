package com.zin.unicorn.pojo;

import java.io.Serializable;

/**
 * Created by ZhuJinMing on 2017/7/21.
 */
public class CostPoJo implements Serializable {

    private String mark;
    private String name;
    private long money;

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "CostPoJo{" +
                "mark='" + mark + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
