package com.zin.unicorn.event;

/**
 * update user info event
 * Created by zinmm on 17/3/20.
 */
public class UpdateUserInfoEvent {
    public static UpdateUserInfoEvent newInstance() {
        return new UpdateUserInfoEvent();
    }
}
