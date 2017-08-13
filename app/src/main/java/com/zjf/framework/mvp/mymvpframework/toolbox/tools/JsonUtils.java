package com.zjf.framework.mvp.mymvpframework.toolbox.tools;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhul on 2015/12/10.
 */
public class JsonUtils {

    private static String TAG = JsonUtils.class.getSimpleName();

    /**
     * 获取JsonObject里的Object
     * @param jsonObj
     * @param key 指定的key
     * @return 取到则返回对应的object
     */
    public Object get(JSONObject jsonObj, String key) {
        try {
            if (jsonObj.has(key)) {
                return jsonObj.get(key);
            } else {
                return new Object();
            }
        } catch (JSONException e) {
            Log.e(TAG, "json get value error!!! key: ".concat(key));
            return new Object();
        }
    }

    /**
     * 获取JsonObject里的String
     * @param jsonObj
     * @param key 指定的key
     * @return 取到则返回对应的值，取不到则返回空字符串""
     */
    public String getString(JSONObject jsonObj, String key) {
        try {
            if (jsonObj.has(key)) {
                return jsonObj.getString(key);
            } else {
                return "";
            }
        } catch (JSONException e) {
            Log.e(TAG, "json get value error!!! key: ".concat(key));
            return "";
        }
    }

    /**
     * 获取JsonObject里的boolean
     * @param jsonObj
     * @param key 指定的key
     * @return 取到则返回对应的值，取不到则返回false
     */
    public boolean getBoolean(JSONObject jsonObj, String key) {
        try {
            if (jsonObj.has(key)) {
                return jsonObj.getBoolean(key);
            } else {
                return false;
            }
        } catch (JSONException e) {
            Log.e(TAG, "json get value error!!! key: ".concat(key));
            return false;
        }
    }

    /**
     * 获取JsonObject里的Double
     * @param jsonObj
     * @param key 指定的key
     * @return 取到则返回对应的值，取不到则返回0d
     */
    public double getDouble(JSONObject jsonObj, String key) {
        try {
            if (jsonObj.has(key)) {
                return jsonObj.getDouble(key);
            } else {
                return 0d;
            }
        } catch (JSONException e) {
            Log.e(TAG, "json get value error!!! key: ".concat(key));
            return 0d;
        }
    }

    /**
     * 获取JsonObject里的int
     * @param jsonObj
     * @param key 指定的key
     * @return 取到则返回对应的值，取不到则返回0
     */
    public int getInt(JSONObject jsonObj, String key) {
        try {
            if (jsonObj.has(key)) {
                return jsonObj.getInt(key);
            } else {
                return 0;
            }
        } catch (JSONException e) {
            Log.e(TAG, "json get value error!!! key: ".concat(key));
            return 0;
        }
    }

    /**
     * 获取JsonObject里的long
     * @param jsonObj
     * @param key 指定的key
     * @return 取到则返回对应的值，取不到则返回0
     */
    public long getLong(JSONObject jsonObj, String key) {
        try {
            if (jsonObj.has(key)) {
                return jsonObj.getLong(key);
            } else {
                return 0l;
            }
        } catch (JSONException e) {
            Log.e(TAG, "json get value error!!! key: ".concat(key));
            return 0l;
        }
    }

    public JSONArray getArray(JSONObject jsonObj, String key){
        try {
            if (jsonObj.has(key)) {
                return jsonObj.getJSONArray(key);
            } else {
                return null;
            }
        } catch (JSONException e) {
            Log.e(TAG, "json get value error!!! key: ".concat(key));
            return null;
        }
    }

    public JSONObject createJSONObject(String json) {
        JSONObject jsonObj = null;
        if(null == json){
            jsonObj = new JSONObject();
        }else {
            try {
                jsonObj = new JSONObject(json);
            } catch (JSONException e) {
                e.printStackTrace();
                jsonObj = new JSONObject();
            }
        }
        return jsonObj;
    }

    /**
     * 返回两个JsonArray的合并后的字符串
     * @param array1
     * @param array2
     * @return
     */
    public final String joinJSONArrayToString(JSONArray array1, JSONArray array2) {
        StringBuffer buffer = new StringBuffer();
        try {
            int len = array1.length();
            for (int i = 0; i < len; i++) {
                JSONObject obj1 = (JSONObject) array1.get(i);
                if (i == len - 1)
                    buffer.append(obj1.toString());
                else
                    buffer.append(obj1.toString()).append(",");
            }
            len = array2.length();
            if (len > 0)
                buffer.append(",");
            for (int i = 0; i < len; i++) {
                JSONObject obj1 = (JSONObject) array2.get(i);
                if (i == len - 1)
                    buffer.append(obj1.toString());
                else
                    buffer.append(obj1.toString()).append(",");
            }
//          buffer.insert(0, "[").append("]");
            return buffer.toString();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 返回两个JsonArray的合并后的字符串
     * @param array1
     * @param array2
     * @return
     */
    public final JSONArray joinJSONArray(JSONArray array1, JSONArray array2) {
        JSONArray array = null;
        String json = joinJSONArrayToString(array1, array2);
        if(null == json){
            array = new JSONArray();
        }else{
            try {
                array = new JSONArray(json);
            } catch (JSONException e) {
                e.printStackTrace();
                array = new JSONArray();
            }
        }
        return array;
    }
}
