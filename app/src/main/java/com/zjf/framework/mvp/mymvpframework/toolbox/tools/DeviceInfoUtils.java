package com.mifpay.toolbox.tools;

/**
 * Created by zhul on 2015/12/9.
 */
public class DeviceInfoUtils {


    /**
     * 未知标识
     * 取不到相应的值时就返回该标识
     */
    private static final String UNKOWN = "UNKOWN";

    /**
     * 获取设备名称（如：XXX的手机）
     * 需要在安卓工程中AndroidManifest中添加权限
     *
     * @return 返回设备名称，如果取不到则返回UNKONW
     */
    public final String getDeviceName() {

        if (null != android.os.Build.MANUFACTURER
                && (!android.os.Build.MANUFACTURER.equals(""))) {
            return android.os.Build.MANUFACTURER;
        } else {
            return UNKOWN;
        }

    }

    /**
     * 获取设备型号（如：小米4、iPhone5s）
     * 需要在安卓工程中AndroidManifest中添加权限
     *
     * @return 返回设备型号，如果取不到则返回UNKONW
     */
    public final String getDeviceModel() {

        if (null != android.os.Build.MODEL
                && (!android.os.Build.MODEL.equals(""))) {
            return android.os.Build.MODEL;
        } else {
            return UNKOWN;
        }

    }

    /**
     * 获取设备系统版本（如：4.4.2 或 8.2）
     * 需要在安卓工程中AndroidManifest中添加权限
     *
     * @return 返回设备系统版本，如果取不到则返回UNKONW
     */
    public final String getDeviceSysVer() {

        if (null != android.os.Build.VERSION.RELEASE
                && (!android.os.Build.VERSION.RELEASE.equals(""))) {
            return android.os.Build.VERSION.RELEASE;
        } else {
            return UNKOWN;
        }

    }
}
