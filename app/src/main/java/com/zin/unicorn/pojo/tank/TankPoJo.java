package com.zin.unicorn.pojo.tank;

import java.io.Serializable;

/**
 * Created by ZhuJinMing on 2017/8/18.
 */
public class TankPoJo implements Serializable {

    // 名字
    private String name;
    // 颜色
    private int color;
    // 血量
    private int hp;
    // 生命
    private int life;
    // 坦克是否还活着
    private boolean alive;

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
