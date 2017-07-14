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

package com.zin.toolutils.fix;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// @formatter:off

/**
 * 解决 android.sec.clipboard.ClipboardUIManager 内存泄露问题
 * 此问题应该只在三星手机上出现, 属于三星系统的一个 bug
 * <p>
 * 原因 : ClipboardUIManager getInstance 的方式有问题. 源码如下
 * public static ClipboardUIManager getInstance(Context context) {
 * if (sInstance == null)
 * sInstance = new ClipboardUIManager(context);
 * return sInstance;
 * }
 * <p>
 * 获取ClipboardUIManager对象传的Context实际上就是当前的Activity，这样 activity finish 后就一直被引用没法释放了
 * <p>
 * 解决办法 : 在 Application 中先通过反射调用 getInstance , 让其引用 applicationContext.
 * Created by zinmm on 9/23/15.
 */
// @formatter:on
public class FixClipboardUIManagerLeak {

    public static void fixClipboardUIManagerLeak(Application application){
        String phoneName = android.os.Build.BRAND;
        if (TextUtils.isEmpty(phoneName)) {
            return;
        }

        if (!phoneName.equals("samsung")) {
            return;
        }

        try {
            Class cls = Class.forName("android.sec.clipboard.ClipboardUIManager");
            Method m = cls.getDeclaredMethod("getInstance", Context.class);
            m.setAccessible(true);
            m.invoke(null, application);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}