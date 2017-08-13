package com.zjf.framework.mvp.mymvpframework.toolbox.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by zhul on 2016/3/25.
 *
 * 参考：http://www.sojson.com/blog/21
 */
public class GzipUtils {

    private static String TAG = GzipUtils.class.getSimpleName();
    /**
     * 字符串的压缩
     *
     * @param str
     *            待压缩的字符串
     * @return 返回压缩后的字符串
     * @throws IOException
     */
    public byte[] compress(String str) throws IOException {
        if (null == str || str.length() <= 0) {
            return new byte[0];
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 使用默认缓冲区大小创建新的输出流
        GZIPOutputStream gzip = new GZIPOutputStream(out);
        // 将 b.length 个字节写入此输出流
        gzip.write(str.getBytes("UTF-8"));
        gzip.close();
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toByteArray();
//        return out.toString("UTF-8");
    }
    /**
     * 字符串的解压
     *
     * @param buff
     *            对字符串解压
     * @return 返回解压缩后的字符串
     * @throws IOException
     */
    public String unCompress(byte[] buff) throws IOException {
        if (null == buff || buff.length <= 0) {
            return "";
        }
        // 获取的 byte 数组输出流
        ByteArrayOutputStream out = unCompressToByteArrayOutputStream(buff);
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toString("UTF-8");
    }
    /**
     * 字符串的解压
     *
     * @param buff
     *            对字符串解压
     * @return 返回解压缩后的字符串
     * @throws IOException
     */
    public byte[] unCompressToBytes(byte[] buff) throws IOException {
        if (null == buff || buff.length <= 0) {
            return new byte[]{};
        }
        // 获取的 byte 数组输出流
        ByteArrayOutputStream out = unCompressToByteArrayOutputStream(buff);
        // 使用指定的 charsetName，通过解码字节将缓冲区内容转换为字符串
        return out.toByteArray();
    }

    /**
     * 字符串的解压
     *
     * @param buff
     *            对字符串解压
     * @return 返回解压缩后的字符串
     * @throws IOException
     */
    private ByteArrayOutputStream unCompressToByteArrayOutputStream(byte[] buff) throws IOException {
        if (null == buff || buff.length <= 0) {
            return new ByteArrayOutputStream();
        }
        // 创建一个新的 byte 数组输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        // 创建一个 ByteArrayInputStream，使用 buf 作为其缓冲区数组
//        ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("UTF-8"));
        ByteArrayInputStream in = new ByteArrayInputStream(buff);
        // 使用默认缓冲区大小创建新的输入流
        GZIPInputStream gzip = new GZIPInputStream(in);
        byte[] buffer = new byte[256];
        int n = 0;
        while ((n = gzip.read(buffer)) >= 0) {// 将未压缩数据读入字节数组
            // 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此 byte数组输出流
            out.write(buffer, 0, n);
        }
        return out;
    }
}
