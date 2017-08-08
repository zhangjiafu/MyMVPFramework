package com.zjf.framework.mvp.mymvpframework.common;

import com.google.gson.Gson;

/**
 * Created by Administrator on 2017/8/8.
 */

public class JsonUtils {


    public static String objToJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
}
