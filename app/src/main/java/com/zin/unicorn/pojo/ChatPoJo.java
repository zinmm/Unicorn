package com.zin.unicorn.pojo;

import java.io.Serializable;

/**
 * Create by ZhuJinMing on 2018/06/28
 */
public class ChatPoJo implements Serializable {

    private UserPoJo userPoJo;
    private String date;
    private String content;

    public UserPoJo getUserPoJo() {
        return userPoJo;
    }

    public void setUserPoJo(UserPoJo userPoJo) {
        this.userPoJo = userPoJo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
