package com.zjf.framework.mvp.mymvpframework.toolbox.tools;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by zhul on 2016/3/29.
 */
public class DownloadUtils {

    private static final String TAG = "DownloadUtils";

    /**
     * 用stream的方式下载图片
     * @param urlString
     * @param outputStream
     * @return
     */
    public boolean downloadUrlToStreamGet(String urlString, OutputStream outputStream, int timeoutMillis) {
        Log.d(TAG, "GET urlString=" + urlString);

        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
//            urlString = encode(urlString);
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(timeoutMillis);
            urlConnection.setReadTimeout(timeoutMillis);
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 用stream的方式下载图片
     * @param urlString
     * @param outputStream
     * @return
     */
    public boolean downloadUrlToStreamPost(String urlString, OutputStream outputStream, int timeoutMillis, Map<String, String> mapPostParams) {
        Log.d(TAG, "POST urlString=" + urlString);

        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
//            urlString = encode(urlString);
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(timeoutMillis);
            urlConnection.setReadTimeout(timeoutMillis);
            urlConnection.setRequestMethod("POST");
            urlConnection.setUseCaches(false);// Post 请求不能使用缓存

            // 设置post参数
            OutputStream postOs = urlConnection.getOutputStream();
            String strParam = convertPostParams(mapPostParams);
            postOs.write(strParam.getBytes());

            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将map
     * @param mapPostParams
     * @return
     */
    private String convertPostParams(Map<String, String> mapPostParams) {
        Log.d(TAG, "convertPostParams()==>");
        String strParams = new String();
        if(null == mapPostParams){
            Log.d(TAG, "convertPostParams NO POST_PARAMS!!");
            return strParams;
        }else{
            int index = 0;
            StringBuilder stringBuilder = new StringBuilder();
            for(String key : mapPostParams.keySet()){
                if(index != 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append(key + "=" + mapPostParams.get(key));
                index++;
            }
            strParams = stringBuilder.toString();
            Log.d(TAG, "strParams= " + strParams);
        }
        return strParams;
    }


    public byte[] inputStreamToByteArray(InputStream in){
        Log.d(TAG, "inputStreamToByteArray()==>");

        byte[] in_b = new byte[0];
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[256]; //buff用于存放循环读取的临时数据
            int rc = 0;
            while ((rc = in.read(buff, 0, 256)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            in_b = swapStream.toByteArray(); //in_b为转换之后的结果
            Log.e(TAG, "buffer length=" + in_b.length);

        } catch (IOException e) {

            e.printStackTrace();
        }finally {

            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "buffer length=" + in_b.length);
        return in_b;
    }
}
