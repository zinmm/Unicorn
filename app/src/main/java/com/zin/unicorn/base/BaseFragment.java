package com.zin.unicorn.base;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by zinmm on 12/14/16.
 */
public class BaseFragment extends Fragment {

    private View rootView;

    public View findViewById(int resId) {
        return rootView.findViewById(resId);
    }

}
