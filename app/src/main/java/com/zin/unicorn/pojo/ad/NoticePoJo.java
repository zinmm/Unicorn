package com.zin.unicorn.pojo.ad;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhujinming on 2018/4/9.
 */
public class NoticePoJo implements Serializable {

    // 活动对象
    private List<ActivityPoJo> activityPoJos;
    // 毫秒为单位
    private int frequencyInteval;
    // 密匙
    private String password;

    public List<ActivityPoJo> getActivityPoJos() {
        return activityPoJos;
    }

    public void setActivityPoJos(List<ActivityPoJo> activityPoJos) {
        this.activityPoJos = activityPoJos;
    }

    public int getFrequencyInteval() {
        return frequencyInteval;
    }

    public void setFrequencyInteval(int frequencyInteval) {
        this.frequencyInteval = frequencyInteval;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
