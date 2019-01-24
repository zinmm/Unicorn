package com.zin.unicorn.module.chat.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseAdapter;
import com.zin.unicorn.pojo.ChatPoJo;

import static android.view.Gravity.END;
import static android.view.Gravity.RIGHT;
import static android.view.Gravity.START;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Create by ZhuJinMing on 2018/06/28
 */
public class ChatAdapter extends BaseAdapter<ChatPoJo> {

    public ChatAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateCommon(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.chat_item, null);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindCommon(RecyclerView.ViewHolder holder, ChatPoJo item) {

        if (holder == null || !(holder instanceof ChatViewHolder) || item == null) {
            return;
        }

        String date = item.getDate();
        String content = item.getContent();
        long userId = item.getUserPoJo().getId();

        ChatViewHolder chatViewHolder = (ChatViewHolder) holder;
        chatViewHolder.mChatContentTextView.setText(content);
        chatViewHolder.mChatDateTextView.setText(date);

        chatViewHolder.mChatContentTextView.setGravity(userId == 0 ? START : END);
//        LinearLayout.LayoutParams layoutParams =
//                (LinearLayout.LayoutParams) chatViewHolder.mChatContentTextView.getLayoutParams();
//        layoutParams.weight = MATCH_PARENT;
//        layoutParams.height = WRAP_CONTENT;
//        layoutParams.gravity = userId == 0 ? START : END;
//        chatViewHolder.mChatContentTextView.setLayoutParams(layoutParams);
    }
}
