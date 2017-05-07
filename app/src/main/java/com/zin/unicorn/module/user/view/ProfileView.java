package com.zin.unicorn.module.user.view;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by zhujinming on 17/5/5.
 */

public interface ProfileView {

    ImageView getIvAvatar();
    TextView getTvId();
    TextView getTvUsername();
    TextView getTvPassword();
    TextView getTvGender();

}
