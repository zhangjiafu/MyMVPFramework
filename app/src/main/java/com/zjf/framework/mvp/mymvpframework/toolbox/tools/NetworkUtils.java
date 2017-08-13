package com.zjf.framework.mvp.mymvpframework.toolbox.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by zhul on 2015/12/9.
 */
public class NetworkUtils {

    /** 联网类型-无 */
    public static final int NET_TYPE_NULL = 0;
    /** 联网类型-wifi */
    public static final int NET_TYPE_WIFI = 1;
    /** 联网类型-流量 */
    public static final int NET_TYPE_MOBILE = 2;

    /** no network. */
    public static final int NETWORK_CLASS_NONE = 0;
    /** Unknown network class. */
    public static final int NETWORK_CLASS_UNKNOWN = 1;
    /** Class of broadly defined "2G" networks. */
    public static final int NETWORK_CLASS_2_G = 2;
    /** Class of broadly defined "3G" networks. */
    public static final int NETWORK_CLASS_3_G = 3;
    /** Class of broadly defined "4G" networks. */
    public static final int NETWORK_CLASS_4_G = 4;


    public static int NET_CNNT_BAIDU_OK = 1; // NetworkAvailable
    public static int NET_CNNT_BAIDU_TIMEOUT = 2; // No NetworkAvailable
    public static int NET_NOT_PREPARE = 3; // Net No Ready
    public static int NET_ERROR = 4; // Net Error
    private static int TIMEOUT = 3000; // TIMEOUT


    public  boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * get local ip address
     *
     * @return
     */
    public  String getLocalIpAddress(Context context) {
        String localIp = "";
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
                     enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        localIp = inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return localIp;
    }

    /**
     * get net state
     *
     * @return
     */
    public  int getNetState(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo networkinfo = connectivity.getActiveNetworkInfo();
                if (networkinfo != null) {
                    if (networkinfo.isAvailable() && networkinfo.isConnected()) {
                        if (!connectionNetwork(context)) {
                            return NET_CNNT_BAIDU_TIMEOUT;
                        }
                        else {
                            return NET_CNNT_BAIDU_OK;
                        }
                    }
                    else {
                        return NET_NOT_PREPARE;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return NET_ERROR;
    }

    /**
     * ping "www.baidu.com"
     *
     * @return
     */
    private  boolean connectionNetwork(Context context) {
        boolean result = false;
        HttpURLConnection httpUrl = null;
        try {
            httpUrl = (HttpURLConnection) new URL("www.baidu.com")
                    .openConnection();
            httpUrl.setConnectTimeout(TIMEOUT);
            httpUrl.connect();
            result = true;
        } catch (IOException e) {
        } finally {
            if (null != httpUrl) {
                httpUrl.disconnect();
            }
            httpUrl = null;
        }
        return result;
    }

    /**
     * check is 2G
     *
     * @return boolean
     */
    public  boolean is2G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && (activeNetInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_EDGE
                || activeNetInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_GPRS || activeNetInfo
                .getSubtype() == TelephonyManager.NETWORK_TYPE_CDMA)) {
            return true;
        }
        return false;
    }

    /**
     * check is3G
     *
     * @return boolean
     */
    public  boolean is3G(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }

    /**
     * check is Wifi
     *
     * @return boolean
     */
    public  boolean isWifi(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * check is wifi on
     */
    public  boolean isWifiEnabled(Context context) {
        ConnectivityManager mgrConn = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        TelephonyManager mgrTel = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        return ((mgrConn.getActiveNetworkInfo() != null && mgrConn
                .getActiveNetworkInfo().getState() == NetworkInfo.State.CONNECTED) || mgrTel
                .getNetworkType() == TelephonyManager.NETWORK_TYPE_UMTS);
    }

    /**
     * 获取网络信息
     *
     * @param context
     * @return
     */
    private NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo;
    }

    /**
     * 获取当前联网的类型
     *
     * @return 0: 无网络， 1:WIFI， 2:其他（流量）
     */
    public int getNetType(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        if (!isConnected(networkInfo)) {
            return NetworkUtils.NET_TYPE_NULL;
        }
        int type = networkInfo.getType();
        if (type == ConnectivityManager.TYPE_WIFI) {
            return NetworkUtils.NET_TYPE_WIFI;
        } else if(type == ConnectivityManager.TYPE_MOBILE) {
            return NetworkUtils.NET_TYPE_MOBILE;
        }else{
            return NetworkUtils.NET_TYPE_NULL;
        }
    }

    /**
     * 是否已经联网
     *
     * @param context
     * @return
     */
    public boolean isConnected(Context context) {
        NetworkInfo networkInfo = getNetworkInfo(context);
        return isConnected(networkInfo);
    }

    /**
     * 是否已经联网
     *
     * @param networkInfo
     * @return
     */
    public boolean isConnected(NetworkInfo networkInfo) {
        return networkInfo != null && networkInfo.isConnected();
    }

    /**
     * 是否使用WIFI
     *
     * @param context
     * @return
     */
    public boolean isWifiConnecting(Context context) {
        return getNetType(context) == NetworkUtils.NET_TYPE_WIFI;
    }

    /**
     * 获取运营商编码
     * 一般为IMSI前5位，460 + 0x
     * 本函数返回5位编码，否则返回null
     * 调用本函数需要添加权限：<uses-permissionandroid:name="android.permission.READ_PHONE_STATE" />
     * @param context
     * @return
     */
    public String getCountryAndNetworkCode(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        // 返回唯一的用户ID;就是这张卡的编号神马的
        String imsi = telephonyManager.getSubscriberId();
        if (null != imsi && imsi.length() > 5) {
            String mobileCountryCode = imsi.substring(0, 3);
            String mobileNetworkCode = imsi.substring(3, 5);
            return mobileCountryCode + mobileNetworkCode;
        } else {
            return null;
        }
    }

    /**
     * 获取手机流量等级
     * 不使用流量时返回NetworkUtils.NETWORK_CLASS_NONE
     * 调用本函数需要添加权限：<uses-permissionandroid:name="android.permission.READ_PHONE_STATE" />
     * @param context
     * @return NetworkUtils.NETWORK_CLASS_XX
     */
    public int getFluxLevel(Context context){

        if(getNetType(context) == NET_TYPE_MOBILE){
            // 当前使用手机流量时
            NetworkInfo networkInfo = getNetworkInfo(context);
            return getNetworkClass(networkInfo.getSubtype());
        }else{
            return NetworkUtils.NETWORK_CLASS_NONE;
        }
    }

    /**
     * 获取手机流量等级
     * 调用本函数需要添加权限：<uses-permissionandroid:name="android.permission.READ_PHONE_STATE" />
     * @param networkType networkInfo.getSubtype()
     * @return NetworkUtils.NETWORK_CLASS_XX
     */
    private int getNetworkClass(int networkType) {
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
//            case TelephonyManager.NETWORK_TYPE_GSM:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_CLASS_2_G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
//            case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                return NETWORK_CLASS_3_G;
            case TelephonyManager.NETWORK_TYPE_LTE:
//            case TelephonyManager.NETWORK_TYPE_IWLAN:
                return NETWORK_CLASS_4_G;
            default:
                return NETWORK_CLASS_UNKNOWN;
        }
    }
}
