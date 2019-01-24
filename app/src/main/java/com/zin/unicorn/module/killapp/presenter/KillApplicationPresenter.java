package com.zin.unicorn.module.killapp.presenter;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zin.unicorn.R;
import com.zin.unicorn.base.BasePresenter;
import com.zin.unicorn.module.killapp.view.KillApplicationView;
import com.zin.unicorn.widget.WindowUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ZhuJinMing on 2017/8/28.
 */
public class KillApplicationPresenter extends BasePresenter<KillApplicationView> {

    public void initAllApplicationListView() {


        List<ApplicationInfo> applicationInfos = queryFilterAppInfo(mApplicationContext);

        MyAdapter myAdapter = new MyAdapter(mContext, applicationInfos);
        getView().lvAllApplication().setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    class MyAdapter extends BaseAdapter {

        Context context;
        PackageManager pm;
        List<ApplicationInfo> applicationInfos;

        MyAdapter(Context context, List<ApplicationInfo> applicationInfos) {

            this.pm = context.getApplicationContext().getPackageManager();
            this.context = context;
            this.applicationInfos = applicationInfos;
        }

        @Override
        public int getCount() {
            return applicationInfos.size();
        }

        @Override
        public Object getItem(int i) {
            return applicationInfos.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder viewHolder;
            if (view == null) {

                viewHolder = new ViewHolder();

                view = View.inflate(context, R.layout.application_item, null);
                viewHolder.iconImageView = view.findViewById(R.id.iv_app_icon);
                viewHolder.appNameTextView = view.findViewById(R.id.tv_app_name);
                viewHolder.appIdTextView = view.findViewById(R.id.tv_app_id);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            String name = (String) applicationInfos.get(i).loadLabel(pm);
            String packageName = applicationInfos.get(i).packageName;
            Drawable icon = applicationInfos.get(i).loadIcon(pm);

            viewHolder.iconImageView.setBackgroundDrawable(icon);
            viewHolder.appNameTextView.setText(name);
            viewHolder.appIdTextView.setText(packageName);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    doStartApplicationWithPackageName(packageName);
                }
            });

            viewHolder.iconImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getRunningServiceInfo(context, packageName);
                }
            });

            return view;
        }

        public void getRunningServiceInfo(Context context ,String packageName) {

            WindowUtil.showPopupWindow(context);
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.killBackgroundProcesses(packageName);

            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);

            mActivity.finish();
        }

        private void doStartApplicationWithPackageName(String packagename) {

            // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
            PackageInfo packageinfo = null;
            try {
                packageinfo = context.getApplicationContext().getPackageManager().getPackageInfo(packagename, 0);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (packageinfo == null) {
                return;
            }

            // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
            Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
            resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            resolveIntent.setPackage(packageinfo.packageName);

            // 通过getPackageManager()的queryIntentActivities方法遍历
            List<ResolveInfo> resolveinfoList = context.getApplicationContext().getPackageManager()
                    .queryIntentActivities(resolveIntent, 0);

            ResolveInfo resolveinfo = resolveinfoList.iterator().next();
            if (resolveinfo != null) {
                // packagename = 参数packname
                String packageName = resolveinfo.activityInfo.packageName;
                // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
                String className = resolveinfo.activityInfo.name;
                // LAUNCHER Intent
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);

                // 设置ComponentName参数1:packagename参数2:MainActivity路径
                ComponentName cn = new ComponentName(packageName, className);

                intent.setComponent(cn);
                startActivity(intent);
            }
        }

        class ViewHolder {

            ImageView iconImageView;
            TextView appNameTextView;
            TextView appIdTextView;
        }
    }

    public static List<ApplicationInfo> queryFilterAppInfo(Context context) {
        PackageManager pm = context.getApplicationContext().getPackageManager();
        // 查询所有已经安装的应用程序
        List<ApplicationInfo> appInfos = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);// GET_UNINSTALLED_PACKAGES代表已删除，但还有安装目录的
        List<ApplicationInfo> applicationInfos = new ArrayList<>();

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        // 通过getPackageManager()的queryIntentActivities方法遍历,得到所有能打开的app的packageName
        List<ResolveInfo> resolveinfoList = context.getApplicationContext().getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        Set<String> allowPackages = new HashSet();
        for (ResolveInfo resolveInfo : resolveinfoList) {
            allowPackages.add(resolveInfo.activityInfo.packageName);
        }

        for (ApplicationInfo app : appInfos) {

            if (allowPackages.contains(app.packageName)) {
                applicationInfos.add(app);
            }
        }
        return applicationInfos;
    }
}
