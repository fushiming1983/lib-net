package com.suning.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * Author : 13120678
 * Date   : 17/3/30 下午4:57
 * Desc   : 文件管理工具类
 */
public class FileUtil {

    /**
     * 判断文件是否存在
     * @param path
     * @return
     */
    public static boolean exist(String path) {
        File file = new File(path);
        return file.exists();
    }


    /**
     * InputStream转换为string
     * @param in
     * @return
     * @throws IOException
     */
    public static String inputStreamToString(InputStream in) throws IOException{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            String outputStream = null;
            byte[] data = new byte[1024];
            int count = -1;
            while ((count = in.read(data, 0, 1024)) != -1)
                outStream.write(data, 0, count);
            data = null;
            outputStream = new String(outStream.toByteArray(), "UTF-8");
            return outputStream;
        } finally {
            if (outStream != null) {
                outStream.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * 从Assets中获取string
     * @param context
     * @param fileName
     * @return
     */
    public static String getStrFromAssets(Context context,String fileName) {
        try {
            InputStreamReader reader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            String assetsString = "";
            while ((line = bufferedReader.readLine()) != null) {
                assetsString += line;
            }
            return assetsString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 文件复制
     *
     * @param target 目标路径
     * @param source 原始路径
     */
    public static void copyFile(String target, String source) {
        if (TextUtils.isEmpty(target) || TextUtils.isEmpty(source)) {
            return;
        }
        File sourceFile = new File(source);
        if (!sourceFile.exists()) {
            return;
        }
        FileOutputStream fOut = null;
        FileInputStream fIn = null;
        try {
            fOut = new FileOutputStream(target);
            fIn = new FileInputStream(source);
            byte[] buffer = new byte[1024];
            int readLine = 0;
            while ((readLine = fIn.read(buffer, 0, 1024)) >= 0) {
                fOut.write(buffer, 0, readLine);
            }
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fOut != null) {
                    fOut.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (fIn != null) {
                    fIn.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 保存Bitmap对象到本地
     * @param bitmap
     * @param bitName
     * @throws IOException
     */
    public static File saveBitmap(Bitmap bitmap, String bitName) {
        File file = new File(bitName);
        if(file.exists()){
            file.delete();
        }
        FileOutputStream out;
        try{
            out = new FileOutputStream(file);
            if(bitmap.compress(Bitmap.CompressFormat.PNG, 90, out)) {
                out.flush();
                out.close();
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    /**
     * 通知系统媒体库更新
     * @param context
     * @param path
     */
    public static void galleryAddPic(Context context,String path) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);

        if (TextUtils.isEmpty(path)) {
            return;
        }

        File f = new File(path);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

    /**
     * 存入字节数组
     * @param b
     * @param outputFile
     * @return
     */
    public static File saveByte(byte[] b, String outputFile){
        BufferedOutputStream stream = null;
        File file = null;
        try {
            file = new File(outputFile);
            FileOutputStream fstream = new FileOutputStream(file);
            stream = new BufferedOutputStream(fstream);
            stream.write(b);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            if (stream != null){
                try {
                    stream.close();
                } catch (IOException e1){
                    e1.printStackTrace();
                }
            }
        }
        return file;
    }


}
