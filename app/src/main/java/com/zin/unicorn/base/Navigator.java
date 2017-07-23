package com.zin.unicorn.base;

import android.content.Context;
import android.content.Intent;

import com.zin.unicorn.module.HomePageActivity;
import com.zin.unicorn.module.excel.activity.ExcelActivity;
import com.zin.unicorn.module.user.activity.ProfileActivity;
import com.zin.unicorn.module.cost.activity.CostActivity;

/**
 * Created by zinmm on 16/12/22.
 */
public enum Navigator {

    INSTANCE;

    public void navigateToHome(Context mContext) {

        Intent intent = new Intent(mContext, HomePageActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    public void navigateToProfile(Context mContext) {

        Intent intent = new Intent(mContext, ProfileActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(intent);
    }

    public void navigateToExcel(Context mContext) {

        Intent intent = new Intent(mContext, ExcelActivity.class);
        mContext.startActivity(intent);
    }

    public void navigateToLogin(Context mContext) {

//        Intent intent = new Intent(mContext, SignInPageActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        mContext.startActivity(intent);
    }

    public void navigateToCost(Context mContext) {

        Intent intent = new Intent(mContext, CostActivity.class);
        mContext.startActivity(intent);
    }
}
