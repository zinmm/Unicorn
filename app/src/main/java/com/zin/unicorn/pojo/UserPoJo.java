package com.zin.unicorn.pojo;

import java.io.Serializable;

/**
 * Created by zinmm on 17/3/20.
 */

public class UserPoJo<E> implements Serializable {

    private long id;
    private String userName;
    private String password;
    private String avatar;
    private int gender;
    private E data;

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender == 0 ? "男" : "女";
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserPoJo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                '}';
    }
}
