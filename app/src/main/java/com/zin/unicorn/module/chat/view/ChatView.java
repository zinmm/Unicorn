package com.zin.unicorn.module.chat.view;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Create by ZhuJinMing on 2018/06/26
 */
public interface ChatView {

    public RecyclerView mChatListRecyclerView();
    public EditText mChatContentEditText();
}
