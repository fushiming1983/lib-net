package com.suning.utils;

import android.content.Context;
import android.os.Environment;

import com.suning.framework.utils.AppPackageInfo;

import java.io.File;

/**
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * Author : 13120678
 * Date   : 17/3/30 下午4:12
 * Desc   : 管理本地文件目录工具类
 */
public class LocalFileManager {

    private static final String TAG = LocalFileManager.class.getSimpleName();

    /**
     * 拍照本地图片路径
     */
    public static final String CAMERA_PATH = "camera";
    /**
     * crash本地目录
     */
    public static final String CRASH_PATH = "crash";

    /**
     * 初始化本地文件目录
     * @param context
     */
    public static void init(Context context) {
        createRootFolder(context);
        createCameraFolder(context);
    }

    /**
     * 创建根目录
     * @param context
     * @return
     */
    public static String createRootFolder(Context context) {
        String mRootPath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            mRootPath = Environment.getExternalStorageDirectory().getPath() + File.separator + new AppPackageInfo(context).getAppName();
        } else {
            mRootPath = context.getFilesDir() + File.separator + new AppPackageInfo(context).getAppName();
        }

        File file = new File(mRootPath);
        if (!file.exists()) {
            file.mkdir();
        }
        return mRootPath;
    }


    /**
     * 创建拍摄的图片
     * @param context
     * @return
     */
    public static String createCameraFolder(Context context) {
        String mCameraPath = createRootFolder(context) + File.separator + CAMERA_PATH;
        File file = new File(mCameraPath);
        if (!file.exists()) {
            file.mkdir();
        }
        return mCameraPath;
    }


    /**
     * 创建本地Crash目录
     * @param context
     * @return
     */
    public static String createCrashFolder(Context context) {
        String mCrashPath = createRootFolder(context) + File.separator + CRASH_PATH;
        File file = new File(mCrashPath);
        if(!file.exists()) {
            file.mkdir();
        }
        return mCrashPath;
    }



}
