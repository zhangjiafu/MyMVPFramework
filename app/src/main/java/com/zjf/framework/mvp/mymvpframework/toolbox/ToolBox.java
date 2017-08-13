package com.zjf.framework.mvp.mymvpframework.toolbox;

import com.zjf.framework.mvp.mymvpframework.toolbox.tools.AppInfoUtils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.Base64Utils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.BitmapUtils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.DataUtils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.DateTimeUtils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.DeviceInfoUtils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.DownloadUtils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.FileUtils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.GzipUtils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.JsonUtils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.NetworkUtils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.ScreenUtils;
import com.zjf.framework.mvp.mymvpframework.toolbox.tools.SoftInputUtils;

/**
 * Created by Administrator on 2017/8/8.
 */

public class ToolBox {

    /**
     * App信息工具
     */
    private final static AppInfoUtils mAppInfoUtils = new AppInfoUtils();

    /**
     * 获取-App信息工具
     */
    public static AppInfoUtils getAppInfoUtils() {
        return mAppInfoUtils;
    }

    /**
     * 数据处理工具
     */
    private final static DataUtils mDataUtils = new DataUtils();

    /**
     * 获取-数据处理工具
     */
    public static DataUtils getDataUtils() {
        return mDataUtils;
    }

    /**
     * 设备相关信息处理工具
     */
    private final static DeviceInfoUtils mDeviceInfoUtils = new DeviceInfoUtils();

    /**
     * 获取-设备相关信息处理工具
     */
    public static DeviceInfoUtils getDeviceInfoUtils() {
        return mDeviceInfoUtils;
    }

    /**
     * 屏幕相关信息处理工具
     */
    private final static ScreenUtils mScreenUtils = new ScreenUtils();

    /**
     * 获取-屏幕相关信息处理工具
     */
    public static ScreenUtils getScreenUtils() {

        return mScreenUtils;
    }

    /**
     * 软键盘处理工具
     */
    private final static SoftInputUtils mSoftInputUtils = new SoftInputUtils();

    /**
     * 获取-软键盘处理工具
     */
    public static SoftInputUtils getSoftInputUtils() {

        return mSoftInputUtils;
    }

    /**
     * 网络工具
     */
    private final static NetworkUtils mNetworkUtils = new NetworkUtils();

    /**
     * 获取-网络工具
     */
    public static NetworkUtils getNetworkUtils() {

        return mNetworkUtils;
    }

    /**
     * 日期转换工具
     */
    private final static DateTimeUtils mDateTimeUtils = new DateTimeUtils();

    /**
     * 获取-日期转换工具
     */
    public static DateTimeUtils getDateTimeUtils() {

        return mDateTimeUtils;
    }

    /**
     * 文件工具
     */
    private final static FileUtils mFileUtils = new FileUtils();

    /**
     * 获取-文件工具
     */
    public static FileUtils getFileUtils() {
        return mFileUtils;
    }

    /**
     * Json工具
     */
    private final static JsonUtils mJsonUtils = new JsonUtils();

    /**
     * 获取-Json工具
     */
    public static JsonUtils getJsonUtils() {
        return mJsonUtils;
    }

    /**
     * Bitmap工具
     */
    private final static BitmapUtils mBitmapUtils = new BitmapUtils();

    /**
     * 获取-Bitmap工具
     */
    public static BitmapUtils getBitmapUtils() {
        return mBitmapUtils;
    }


    /**
     * Base64工具
     */
    private final static Base64Utils mBase64Utils = new Base64Utils();

    /**
     * 获取-Base64工具
     */
    public static Base64Utils getBase64Utils() {
        return mBase64Utils;
    }

    /**
     * Gzip工具
     */
    private final static GzipUtils mGzipUtils = new GzipUtils();

    /**
     * 获取-Gzip工具
     */
    public static GzipUtils getGzipUtils() {
        return mGzipUtils;
    }

    /**
     * 下载工具
     */
    private final static DownloadUtils mDownloadUtils = new DownloadUtils();

    /**
     * 获取-下载工具
     */
    public static DownloadUtils getDownloadUtils() {
        return mDownloadUtils;
    }

}
