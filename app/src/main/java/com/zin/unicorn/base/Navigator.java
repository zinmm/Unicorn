package com.zin.unicorn.base;

import android.content.Context;
import android.content.Intent;

import com.zin.unicorn.module.demo.activity.DemoActivity;
import com.zin.unicorn.module.demo.adapter.DemoAdapter;
import com.zin.unicorn.module.home.activity.HomePageActivity;
import com.zin.unicorn.module.excel.activity.ExcelActivity;
import com.zin.unicorn.module.face.FaceDetectorActivity;
import com.zin.unicorn.module.chat.activity.ChatActivity;
import com.zin.unicorn.module.user.activity.ProfileActivity;
import com.zin.unicorn.module.cost.activity.CostActivity;

/**
 * Created by zinmm on 16/12/22.
 */
public enum Navigator {

    INSTANCE;

    /**
     * Demo page
     *
     * @param context
     */
    public void navigateToDemo(Context context) {

        Intent intent = new Intent(context, DemoActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }


    /**
     * New Home
     * 
     * @param context spirit medium
     */
    public void navigateToMain(Context context) {

        Intent intent = new Intent(context, ChatActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    /**
     * home..
     *
     * @see #navigateToMain
     * @param context spirit medium
     */
    @Deprecated
    public void navigateToHome(Context context) {

        Intent intent = new Intent(context, HomePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void navigateToProfile(Context context) {

        Intent intent = new Intent(context, ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public void navigateToExcel(Context context) {

        Intent intent = new Intent(context, ExcelActivity.class);
        context.startActivity(intent);
    }

    public void navigateToLogin(Context context) {

//        Intent intent = new Intent(mContext, SignInPageActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        mContext.startActivity(intent);
    }

    public void navigateToCost(Context context) {

        Intent intent = new Intent(context, CostActivity.class);
        context.startActivity(intent);
    }

    public void navigateToFace(Context context) {
        Intent intent = new Intent(context, FaceDetectorActivity.class);
        context.startActivity(intent);
    }
}
