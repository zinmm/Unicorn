package com.zin.unicorn.pojo.tank;

import java.io.Serializable;

/**
 * Created by ZhuJinMing on 2017/8/18.
 */
public class ShellPojo implements Serializable {

    private int size;
    private int force;
    private int kind;
    private int style;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }
}
