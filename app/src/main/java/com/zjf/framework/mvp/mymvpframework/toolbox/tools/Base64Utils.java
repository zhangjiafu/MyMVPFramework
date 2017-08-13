package com.zjf.framework.mvp.mymvpframework.toolbox.tools;

import android.util.Base64;

/**
 * Created by zhul on 2016/1/18.
 */
public class Base64Utils {
    public byte[] stringToBytes(String str) {

        return Base64.decode(str, Base64.NO_WRAP);
    }
}
