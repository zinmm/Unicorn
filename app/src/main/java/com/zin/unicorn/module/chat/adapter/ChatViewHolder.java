package com.zin.unicorn.module.chat.adapter;

import android.view.View;
import android.widget.TextView;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseViewHolder;

/**
 * Create by ZhuJinMing on 2018/06/28
 */
public class ChatViewHolder extends BaseViewHolder {

    public TextView mChatContentTextView;
    public TextView mChatDateTextView;

    public ChatViewHolder(View itemView) {
        super(itemView);

        mChatContentTextView = itemView.findViewById(R.id.item_chat_content);
        mChatDateTextView = itemView.findViewById(R.id.item_chat_date);
    }
}
