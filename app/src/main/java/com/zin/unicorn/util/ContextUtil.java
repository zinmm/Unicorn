/*
 * Copyright (C) 2016 AdvancingPainters (https://github.com/AdvancingPainters).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zin.unicorn.util;

import android.content.Context;

/**
 * mContext utils
 * Created by zinmm on 5/18/16.
 */
public class ContextUtil {

    private static ContextUtil instance;
    private static Context context;

    public static ContextUtil getInstance() {
        if (instance == null) {
            synchronized (ContextUtil.class) {
                if (instance == null) {
                    instance = new ContextUtil();
                }
            }
        }
        return instance;
    }

    private ContextUtil() {}

    // No need, do not call this method !!
    public void setContext(Context context) {
        ContextUtil.context = context;
    }

    public Context getApplicationContext() {
        Context applicationContext = null;
        if (context != null) {
            applicationContext = context.getApplicationContext();
        }
        return applicationContext;
    }

    protected Context getContext() {
        Context context = null;
        if (ContextUtil.context != null) {
            context = ContextUtil.context;
        }
        return context;
    }

    public void onDestroyContext() {
        context = null;
    }
}
