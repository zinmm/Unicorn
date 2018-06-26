package com.zin.unicorn.pojo.app;

/**
 * Created by zhujinming on 2018/1/11.
 */
public class AppPoJo {

    public AppPoJo(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }

    private String name;

    private String packageName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
