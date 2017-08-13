package com.zjf.framework.mvp.mymvpframework.toolbox.tools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * 屏幕相关信息处理工具
 * Created by zhul on 2015/12/9.
 */
public class ScreenUtils {

    /**
     * 返回设备是否显示了底部导航栏 由于厂商的差异，一些手机会显示，一些手机不显示
     *
     * @return
     */
    public final boolean isNavigationBarShowing(Context c) {

        Resources resources = c.getResources();

        int resourceId = resources.getIdentifier("config_showNavigationBar",
                "bool", "android");
        if (resourceId > 0) {
            return resources.getBoolean(resourceId); // 获取导航栏是否显示true or false
        } else {
            return false;
        }
    }

    /**
     * 获取状态栏高度
     * 1、如果状态栏显示则返回高度
     * 2、如果状态栏不显示则返回0
     *
     * @return
     */
    public final int getNavigationBarHeight(Context c) {

        boolean isNavigationBarShowing = isNavigationBarShowing(c);

        if (isNavigationBarShowing) {
            Resources resources = c.getResources();

            int resourceId = resources.getIdentifier("navigation_bar_height",
                    "dimen", "android");
            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId); // 获取高度
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public final int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public final int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public final  int sp2px(Context context, float spVal)
    {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal)
    {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * 获取屏幕像素密度对于标准160dp的比例
     *
     * @param context
     * @return
     */
    public final float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 获取屏幕分辨率
     *
     * @return 分辨率对象
     */
    public final DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
//		int width = metric.widthPixels;     // 屏幕宽度（像素）
//		int height = metric.heightPixels;   // 屏幕高度（像素）
//		float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
//		int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        return metric;
    }
}
