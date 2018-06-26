package com.zin.unicorn.pojo.ad;

import java.io.Serializable;

/**
 * Created by zhujinming on 2018/4/9.
 */
public class ActivityPoJo implements Serializable {

    // 图标地址
    private String iconUrl;
    // 圆角图标地址
    private String roundedIconUrl;
    // 标题
    private String title;
    // 内容
    private String content;
    // 跳转地址, 加密
    private String scheme;

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getRoundedIconUrl() {
        return roundedIconUrl;
    }

    public void setRoundedIconUrl(String roundedIconUrl) {
        this.roundedIconUrl = roundedIconUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }
}
