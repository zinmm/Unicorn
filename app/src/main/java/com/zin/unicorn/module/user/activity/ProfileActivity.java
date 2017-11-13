package com.zin.unicorn.module.user.activity;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BaseMVPActivity;
import com.zin.unicorn.module.user.presenter.ProfilePresenter;
import com.zin.unicorn.module.user.view.ProfileView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ZhuJinMing on 17/4/26.
 */

public class ProfileActivity extends BaseMVPActivity<ProfileView, ProfilePresenter> implements ProfileView {

    public static final int REQUEST_CODE_PICK = 300;
    public static final int REQUEST_CODE_PHOTO_ZOOM = 301;
    public static final int SUCCESS = -1;

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

    @OnClick(R.id.iv_avatar)
    public void onViewClicked() {
        mPresenter.startPick();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mPresenter.activityResult(requestCode, resultCode, data);
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
