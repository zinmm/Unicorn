package com.zin.unicorn.module.chat.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.unicorn.brain.ShowTabBarListener;
import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.base.BaseTopBarView;
import com.zin.unicorn.module.chat.presenter.ChatPresenter;
import com.zin.unicorn.module.chat.view.ChatView;

import java.io.File;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;
import static android.provider.MediaStore.EXTRA_OUTPUT;
import static com.zin.unicorn.module.chat.presenter.ChatPresenter.REQUEST_CAMERA;

/**
 * Create by ZhuJinMing on 2018/06/26
 */
public class ChatActivity extends
        BaseMVPActivity<ChatView, ChatPresenter> implements ChatView, ShowTabBarListener {

    @Override
    protected void initData() {
        mPresenter.init();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected ChatPresenter createPresenter() {
        return new ChatPresenter();
    }

    @Override
    protected int rootLayoutId() {
        return R.layout.chat_activity;
    }

    @Override
    public void setTopBarViewContent(LinearLayout leftLinearLayout,
                                     LinearLayout centerLinearLayout,
                                     LinearLayout rightLinearLayout) {
        BaseTopBarView.backImageView(mContext, leftLinearLayout);
        BaseTopBarView.imageView(mContext, R.drawable.ic_camera, rightLinearLayout);
        rightLinearLayout.setOnClickListener(v -> {
            // 启动系统相机
            Intent intent = new Intent(ACTION_IMAGE_CAPTURE);
            // 传递路径
            Uri photoUri = Uri.fromFile(
                    new File(Environment.getExternalStorageDirectory().getPath() + "/x.jpg"));
            // 更改系统默认存储路径
            intent.putExtra(EXTRA_OUTPUT, photoUri);
            startActivityForResult(intent, REQUEST_CAMERA);
        });
    }

    @Override
    protected ShowTabBarListener getShowTabBarListener() {
        return this;
    }

    @Override
    public RecyclerView mChatListRecyclerView() {
        return findViewById(R.id.chat_list);
    }

    @Override
    public EditText mChatContentEditText() {
        return findViewById(R.id.chat_content);
    }
}
