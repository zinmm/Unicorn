package com.zin.toolutils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.webkit.DownloadListener;
import android.webkit.WebView;

import com.zin.toolutils.sys.ApplicationListUtil;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        List<ApplicationInfo> applicationInfoList = ApplicationListUtil.queryFilterAppInfo(appContext);
        for (int i = 0; i < applicationInfoList.size(); i++) {
            ApplicationInfo applicationInfo = applicationInfoList.get(i);
            String name = applicationInfo.name;
            String packageName = applicationInfo.packageName;

            System.out.println(name + " : " + packageName);
        }

        assertEquals("com.zin.toolutils.test", appContext.getPackageName());
    }
}
