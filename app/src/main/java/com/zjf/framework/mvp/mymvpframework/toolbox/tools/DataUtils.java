package com.zjf.framework.mvp.mymvpframework.toolbox.tools;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 数据处理工具
 * Created by zhul on 2015/12/9.
 */
public class DataUtils {

    private final static String TAG = "DataUtils";


    /*
    * 检测是否有emoji表情
    *
    * @param source
    * @return
    */
    public final boolean isContainsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private  boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return true:字符串为null或者length为0
     */
    public final boolean isEmpty(CharSequence s) {
        return TextUtils.isEmpty(s);
    }

    /**
     * 判断字符串是否不为空
     *
     * @param s
     * @return
     */
    public final boolean isNotEmpty(CharSequence s) {
        return !isEmpty(s);
    }

    /**
     * 判断字符串是否全是数字
     *
     * @param s
     * @return
     */
    public final boolean isDigitsOnly(CharSequence s) {
        if (isEmpty(s)) {
            return false;
        } else {
            return TextUtils.isDigitsOnly(s);
        }
    }

    /**
     * 把11位的手机号码显示时，中间四位为*
     * 如：13737985833 显示为137****5833
     *
     * @param mobile 手机号码
     * @return 转换完成的string
     */
    public final String getFomatMobile(String mobile) {
        String formatMobile = "";
        if (mobile != null && !mobile.equals("")) {

            formatMobile = mobile.substring(0, 3)
                    + "****"
                    + mobile.substring(mobile.length() - 4, mobile.length());
        }

        return formatMobile;
    }


    /**
     * double型转换成string(保留小数位数，是否添加正负号)
     *
     * @param dblData       待转换的double数据
     * @param decimalPlaces 保留小数的位数
     * @param isWithSimbol  是否添加正负号
     * @return 转换完成的string
     */
    public final String toStringWithDecimalPoint(double dblData, int decimalPlaces, boolean isWithSimbol) {
        String format = "%." + decimalPlaces + "f";
        String result = String.format(format, dblData);
        if (isWithSimbol) {
            if (dblData >= 0) {
                result = "+".concat(result);
            }
        } else {
            if (dblData < 0) {
                result = result.replace("-", "");
            }
        }
        return result;

//		String strFormat = "#0";
//		if(decimalPlaces > 0){
//			StringBuilder sb = new StringBuilder();
//			for(int i =0;i<decimalPlaces;i++){
//				if(i==0){
//					sb.append(".");
//				}
//				sb.append("0");
//			}
//			strFormat = strFormat + sb.toString();
//		}
//		DecimalFormat decimalFormat = new DecimalFormat(strFormat);
//		String strData = decimalFormat.format(dblData);
//		if(dblData < 0){
//			strData = strData.replace("-", "");
//			strData = "-" + strData;
//		}else{
//			if(isWithSimbol){
//				if(dblData < 0){
//					strData = strData.replace("-", "");
//					strData = "-" + strData;
//				}else{
//					strData = strData.replace("+", "");
//					strData = "+" + strData;
//				}
//			}
//		}
////		if(isWithSimbol){
////			if(dblData < 0){
////				strData = strData.replace("-", "");
////				strData = "-" + strData;
////			}else{
////				strData = strData.replace("+", "");
////				strData = "+" + strData;
////			}
////		}
//		return strData;
    }

    /**
     * 拼接字节数组
     *
     * @param bytesArray
     * @return
     */
    public final byte[] concatBytes(byte[]... bytesArray) {

        int allBytesLength = 0;
        for (byte[] bytes : bytesArray) {
            allBytesLength += bytes.length;
        }

        int startIndex = 0;
        byte[] concatedBytes = new byte[allBytesLength];
        for (byte[] bytes : bytesArray) {
            System.arraycopy(bytes, 0, concatedBytes, startIndex, bytes.length);
            startIndex += bytes.length;
        }

        return concatedBytes;
    }

    /**
     * 获取字节数组的16进制显示字符串
     *
     * @param bytes
     * @return
     */
    public final String getHex(byte[] bytes) {
        return getHex(bytes, true);
    }
    /**
     * 获取字节数组的16进制显示字符串
     *
     * @param bytes
     * @return
     */
    public final String getHex(byte[] bytes, boolean isAddSpace) {
        if (null == bytes) {
            return "";
        }
        if (bytes.length < 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
            if(isAddSpace) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();

    }

    /**
     * JSONObject转byte[]
     * @param jsonObject
     * @return
     */
    public final byte[] JsonObjToBytes(JSONObject jsonObject){

        if(null == jsonObject){
            Log.e(TAG, "jsonObject : NULL !!");
            return new byte[0];
        }else{
            String jsonString = jsonObject.toString();
            Log.i(TAG, "jsonData : " + jsonString);
            return jsonString.getBytes();
        }
    }

    /**
     * 转换数据为byte[]
     *
     * @param src
     * @param length
     * @return
     */
    public final byte[] toBytes(int src, int length) {
        byte[] bytesResult = new byte[length];
        for (int i = 0; i < length; i++) {
            bytesResult[i] = (byte) (src >> (i * 8));
        }
        return bytesResult;
    }

    /**
     * 转换数据为byte[]
     *
     * @param src
     * @param length
     * @return
     */
    public final byte[] toBytes(long src, int length) {
        byte[] bytesResult = new byte[length];
        for (int i = 0; i < length; i++) {
            bytesResult[i] = (byte) (src >> (i * 8));
        }
        return bytesResult;
    }

    /**
     * 获取当天日期对应的YYYYMMSS格式的int型数据
     *
     * @return
     */
    public final int getTodayYYYYMMSS_Integer() {
        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String strYYYYMMDD = formatter.format(today);
        return Integer.parseInt(strYYYYMMDD);
    }

    /**
     * 字符串转成int型
     *
     * @return
     */
    public final int toInt(String str) {
        if (isDigitsOnly(str)) {
            return Integer.parseInt(str);
        } else {
            return 0;
        }
    }

    /**
     * 字节数组 => int
     *
     * @param buff
     * @return
     */
    public int bytesToInt(byte[] buff) {

        int length = buff.length;
        int value = 0;
        for (int i = 0; i < length; i++) {
            value += ( (0x000000FF & buff[i]) << (8 * i) );
        }

//        int value = ((0x000000FF & buff[3]) << 24)
//                + ((0x000000FF & buff[2]) << 16)
//                + ((0x000000FF & buff[1]) << 8)
//                + (0x000000FF & buff[0]);
        return value;
    }

    /**
     * 字节数组 => long
     *
     * @param buff
     * @return
     */
    public long bytesToLong(byte[] buff) {

        int length = buff.length;
        long value = 0l;
        for (int i = 0; i < length; i++) {
            value += ( (0x00000000000000FF & buff[i]) << (8 * i) );
        }

//        int value = ((0x000000FF & buff[3]) << 24)
//                + ((0x000000FF & buff[2]) << 16)
//                + ((0x000000FF & buff[1]) << 8)
//                + (0x000000FF & buff[0]);
        return value;
    }

    /**
     * 截取字节数组
     *
     * @param bytesOriginal
     * @param startIndex
     * @param length
     * @return
     */
    public byte[] subBytes(byte[] bytesOriginal, int startIndex, int length) {
        if (startIndex > bytesOriginal.length - 1) {
            return new byte[length];
        }
        if (startIndex + length > bytesOriginal.length) {
            return new byte[length];
        }

        byte[] bytesResult = new byte[length];
        for (int i = 0; i < length; i++) {
            bytesResult[i] = bytesOriginal[startIndex + i];
        }
        return bytesResult;
    }

    /**
     * 字符串转Float
     * @param str
     * @return
     */
    public float toFloat(String str) {

        float balance = 0.00f;
        try{
            balance = Float.valueOf(str);
//            Double dblBalance = Double.valueOf(str);
//            balance = dblBalance.floatValue();
            return balance;
        }catch (Exception e){
            e.printStackTrace();
            return balance;
        }
    }

    /**
     * 复制字节数组
     * @param src
     * @return
     */
    public byte[] copyBytes(byte[] src) {
        if(null == src){
            return new byte[0];
        }else {
            byte[] dest = new byte[src.length];
            System.arraycopy(src, 0, dest, 0, src.length);
            return dest;
        }
    }

    /**
     * 校验和
     * @param arrayBytes
     * @return
     */
    public byte checkSum(byte[]... arrayBytes) {

        int arrayLength = arrayBytes.length;

        // 累加接收到的数据的和，不包含头部协议
        byte c = 0x00;
        for(int i = 0;i< arrayLength ; i++){

            byte[] bufTmp = arrayBytes[i];
            int tmpLength = bufTmp.length;
            for(int j=0;j<tmpLength;j++){
//                c += bufTmp[j];
                c = (byte) (c ^ bufTmp[j]);
            }
        }
        return c;
    }

    /**
     * @param digit
     * @param yourNum
     * @return
     */
    public String padLeftZero(int digit, int yourNum){
        String str = String.format("%0" + digit + "d", yourNum);
        return str;
    }

    /**
     * 秒数转换成  xx小时xx分xx秒
     * @param allSec
     * @return
     */
    public String sToHHmmss(int allSec) {
        Log.d(TAG, "sToHHmmss()==> allSec=" + allSec);
        int h = allSec / 3600;
        int min = allSec % 3600;
        int m = min / 60;
        int s = min % 60;
        Log.d(TAG, "h=" + h);
        Log.d(TAG, "min=" + min);
        Log.d(TAG, "m=" + m);
        Log.d(TAG, "s=" + s);

        String result;
        String format;
        if(h > 0){
            format = "HH:mm:ss";
            result = format.replace("HH", padLeftZero(2, h)).replace("mm", padLeftZero(2, m)).replace("ss", padLeftZero(2, s));
        }else{
            format = "mm:ss";
            result = format.replace("mm", padLeftZero(2, m)).replace("ss", padLeftZero(2, s));
        }
        Log.d(TAG, "result=" + result);
        return result;
    }

    /**
     * hex字符串转byte数组<br/>
     * 2个hex转为一个byte
     * @param src
     * @return
     */
    public byte[] hex2Bytes(String src){
        Log.d(TAG, "hex2Bytes()==> src:" + src);
        byte[] res = new byte[src.length()/2];
        char[] chs = src.toCharArray();
        for(int i=0,c=0; i<chs.length; i+=2,c++){
            res[c] = (byte) (Integer.parseInt(new String(chs,i,2), 16));
        }
        Log.d(TAG, "hex2Bytes()==> result:" + res);
        return res;
    }

    /**
     * 去除空格
     * @param str
     * @return
     */
    public String trim(String str) {
        char[] chars = str.toString().replace(" ", "").toCharArray();
        int len = chars.length;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(chars[i]);// 添加字符
        }
        // 设置新的字符到文本
        String newText = sb.toString();
        return newText;
    }

    /**
     * 将字节数组转换成IP字符串
     * @param bytesIp 4个字节
     * @return
     */
    public String bytesToIp(byte[] bytesIp) {
        String ip = null;
        if(null == bytesIp
                || (null != bytesIp && bytesIp.length != 4)){
            ip = "0.0.0.0";
        }else{
            int index = -1;
            ip = (bytesIp[++index] & 0xFF)
                    + "." + (bytesIp[++index] & 0xFF)
                    + "." + (bytesIp[++index] & 0xFF)
                    + "." + (bytesIp[++index] & 0xFF);
        }
        return ip;
    }

    /**
     * 判断字符串是否是字母  --
     * @param str
     * @return true:一个字符字母 false:不是字母或多个字符字母
     */
    public boolean isLetter(String str){
        if(null == str || "".equals(str)){
            return false;
        }

        String regex = "^[a-zA-Z]$";

        return str.matches(regex);
    }

    /**
     * 判断字符串是否是汉字
     * @param str
     * @return true:是汉字  false:不是汉字
     */
    public boolean isChineseCharacters(String str){
        if(null == str || "".equals(str)){
            return false;
        }
        String regex = "^[0-9a-zA-Z\u4e00-\u9fa5]+$";
        return str.matches(regex);
    }

    /**
     * 将图片url转换成MD5，作为DiskCache的key
     *
     * @param url
     * @return
     */
    public String hashKeyForDisk(String url) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());

            cacheKey = getHex(mDigest.digest(), false);
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(url.hashCode());
        }
        return cacheKey;
    }
}
