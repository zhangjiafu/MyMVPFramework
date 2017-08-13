package com.mifpay.toolbox.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.util.Log;

import java.util.List;

/**
 * Created by zhul on 2015/12/9.
 */
public class AppInfoUtils {

    private static final String TAG = AppInfoUtils.class.getSimpleName();
    private static final String UNKONW_VERSION = "未知版本";

    /**
     * 获取包信息
     *
     * @param context
     * @return
     */
    public final PackageManager getPackageManager(Context context) {
        return context.getPackageManager();
    }

    /**
     * 获取当前APP的Package信息
     *
     * @param context
     * @return
     */
    public final PackageInfo getPackageInfo(Context context) {

        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前版本号(展示用户的版本名称)
     *
     * @param context 当前上下文
     * @return 当前版本号(展示用户的版本名称)
     */
    public final String getVersionName(Context context) {
        PackageInfo pi = getPackageInfo(context);
        if (null != pi) {
            return pi.versionName;
        }
        else {
            return UNKONW_VERSION;
        }
    }

    /**
     * 获取当前版本号(内部识别号)
     *
     * @param context 当前上下文
     * @return 当前版本号(内部识别号)
     */
    public final int getVersionCode(Context context) {
        PackageInfo pi = getPackageInfo(context);
        if (null != pi) {
            return pi.versionCode;
        }
        else {
            return 0;
        }
    }

    /**
     * 获取Application信息
     *
     * @param context
     * @return
     */
    public final ApplicationInfo getApplicationInfo(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = getPackageManager(context);
            PackageInfo pi = getPackageInfo(context);
            applicationInfo = packageManager.getApplicationInfo(pi.packageName, 0);
        }
        catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        return applicationInfo;
    }

    /**
     * 获取Application名称
     * 判断是否取到了应用名称，有则返回，无则返回空字符串
     *
     * @param context
     * @return
     */
    public final String getApplicationName(Context context) {
        PackageManager packageManager = getPackageManager(context);
        ApplicationInfo applicationInfo = getApplicationInfo(context);

        String applicationName = null;
        if (null != packageManager && null != applicationInfo) {
            // 如果两个都不为空，则返回取到的应用名称
            applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        }
        // 判断是否取到了应用名称，有则返回，无则返回空字符串
        if (null != applicationName) {
            return applicationName;
        }
        else {
            return "";
        }
    }

    /**
     * 返回app运行状态
     * 1:程序在前台运行
     * 2:程序在后台运行
     * 3:程序未启动
     * 注意：需要配置权限<uses-permission android:name="android.permission.GET_TASKS" />
     */
    public final int getAppSatus(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> list = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : list) {
            if (appProcess.processName.equals(context.getPackageName())) {
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(TAG, "处于后台" + appProcess.processName);
                    return 2;
                }
                else {
                    Log.i(TAG, "处于前台" + appProcess.processName);
                    return 1;
                }
            }
        }

        return 3;
    }
}
