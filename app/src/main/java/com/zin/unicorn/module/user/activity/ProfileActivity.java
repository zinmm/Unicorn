package com.zin.unicorn.module.user.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.module.user.presenter.ProfilePresenter;
import com.zin.unicorn.module.user.view.ProfileView;

import butterknife.BindView;

/**
 * Created by zhujinming on 17/4/26.
 */

public class ProfileActivity extends BaseMVPActivity<ProfileView, ProfilePresenter> implements ProfileView {

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_id)
    TextView tvId;
    @BindView(R.id.tv_gender)
    TextView tvGender;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_password)
    TextView tvPassword;

    @Override
    protected void initData() {
        mPresenter.requestGetUserInfo();
    }

    @Override
    protected ProfilePresenter createPresenter() {
        return new ProfilePresenter();
    }

    @Override
    protected int rootLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    public ImageView getIvAvatar() {
        return ivAvatar;
    }

    @Override
    public TextView getTvId() {
        return tvId;
    }

    @Override
    public TextView getTvUsername() {
        return tvUsername;
    }

    @Override
    public TextView getTvPassword() {
        return tvPassword;
    }

    @Override
    public TextView getTvGender() {
        return tvGender;
    }
}
