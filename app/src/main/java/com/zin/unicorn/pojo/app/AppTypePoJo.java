package com.zin.unicorn.pojo.app;

import java.util.List;

/**
 * Created by zhujinming on 2018/1/11.
 */
public class AppTypePoJo {

    public AppTypePoJo(String type, List<AppPoJo> apps) {
        this.type = type;
        this.apps = apps;
    }

    private String type;

    private List<AppPoJo> apps;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<AppPoJo> getApps() {
        return apps;
    }

    public void setApps(List<AppPoJo> apps) {
        this.apps = apps;
    }
}
