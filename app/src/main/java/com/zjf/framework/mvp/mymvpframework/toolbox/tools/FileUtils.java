package com.zjf.framework.mvp.mymvpframework.toolbox.tools;

import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;

/**
 * Created by zhul on 2015/12/10.
 */
public class FileUtils {

    private static final String TAG = FileUtils.class.getSimpleName();

    /**
     * 保存文本文件
     * 需要权限：
     * 权限 创建或删除SD卡文件
     * <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
     * 权限 读写SD卡文件
     * <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
     * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
     *
     * @param path     保存路径
     * @param fileName 文件名，需要要包含后缀
     * @param content  文本内容
     * @param isAdd    true:表示以追加形式写文件 false:新建文件
     * @return
     */
    public boolean saveTextFile(String path, String fileName, String content, boolean isAdd) {
        try {
            File fileDir = new File(path);
            if (false == fileDir.exists()) {
                fileDir.mkdirs();
            }
            fileName = path + fileName;
            Log.i(TAG, "save fileName:" + fileName);
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fileName, isAdd);
            writer.write(content);
            writer.close();
            return true;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing report file..."
                    + fileName, e);
            return false;
        }
    }

    /**
     * 根据文件名前缀，删除指定文件夹中所有文件
     *
     * @param dirPath        文件夹
     * @param fileNamePrefix 文件名前缀
     */
    public void deleteFileByPrefix(String dirPath, final String fileNamePrefix) {
        File fileDir = new File(dirPath);
        if (false == fileDir.exists()) {
            Log.i(TAG, "DirPath NOT EXSIT! DO NOT NEED REMOVE!");
            return;
        }
        // 设置文件名过滤器，前缀为指定前缀的才计算个数
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.startsWith(fileNamePrefix)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        // 循环取到的所有文件，逐个删除
        File[] files = fileDir.listFiles(filenameFilter);
        for (int i = 0; i < files.length; i++) {
            files[i].delete();
        }
    }

    /**
     * 根据文件名前缀，获取指定文件夹中的文件个数
     *
     * @param dirPath        文件夹
     * @param fileNamePrefix 文件名前缀
     * @return
     */
    public int getFileCountByPrefix(String dirPath, final String fileNamePrefix) {
        File fileDir = new File(dirPath);
        if (false == fileDir.exists()) {
            Log.i(TAG, "DirPath NOT EXSIT! RETURN FILECOUNT 0 !!");
            return 0;
        }
        // 设置文件名过滤器，前缀为指定前缀的才计算个数
        FilenameFilter filenameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.startsWith(fileNamePrefix)) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        // 循环取到的所有文件，逐个删除
        File[] files = fileDir.listFiles(filenameFilter);
        return files.length;
    }

    /**
     * 删除文件夹
     * @param dirPath
     */
    public void deleteFolder(String dirPath) {
        File fileDir = new File(dirPath);
        if (false == fileDir.exists()) {
            Log.i(TAG, "DirPath NOT EXSIT! DO NOT NEED REMOVE!");
            return;
        }else{
            fileDir.delete();
        }
    }
}
