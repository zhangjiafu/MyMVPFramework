package com.zjf.framework.mvp.mymvpframework.tool;

import com.zjf.framework.mvp.mymvpframework.tool.utils.NetworkUtil;

/**
 * Created by Administrator on 2017/8/8.
 */

public class ToolBox {

    public static NetworkUtil getNetworkUtil(){
        return new NetworkUtil();
    }
}
