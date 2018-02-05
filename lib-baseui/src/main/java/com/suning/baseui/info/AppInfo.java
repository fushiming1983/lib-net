package com.suning.baseui.info;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.suning.baseui.log.Logger;
import com.suning.baseui.utlis.AppUtil;

import java.text.SimpleDateFormat;

/**
 * 应用名称 // 应用包名 // 安装时间 // appkey // 内部版本号 // 外部版本号 // 数据库路径 // 缓存目录 // 缓存大小
 *
 * @Author dmh
 * @ClassName AppInfo
 * @Date 2017-06-06 下午5:02:58
 */
public class AppInfo {

    /**
     * 应用名称
     */
    public String appName = "";

    /**
     * 应用包名
     */
    public String packageName = "";

    /**
     * 外部版本号
     */
    public String versionName = "";

    /**
     * 内部版本号
     */
    public int versionCode = 0;

    /**
     * 应用图标
     */
    public Drawable appIcon = null;

    /**
     * 首次安装时间
     */
    public String firstInstallTime = "";

    /**
     * 打印应用信息
     *
     * @param @param  context
     * @param @return
     * @return String
     */
    public static String print(Context context) {
        AppInfo app = getAppInfo(context);
        String info = "应用信息 appName:" + app.appName + " ";
        info += "packageName:" + app.packageName + " ";// 包名；
        info += "versionName:" + app.versionName + " ";// 外部版本号；
        info += "versionCode:" + app.versionCode + " ";// 内部版本号
        info += "firstInstallTime:" + app.firstInstallTime + " ";// 首次安装时间
        return info;
    }

    /**
     * 获取应用对象数据
     *
     * @param @param  context
     * @param @return
     * @return AppInfo
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static AppInfo getAppInfo(Context context) {
        PackageInfo pi = AppUtil.getPackageInfo(context);
        AppInfo info = new AppInfo();
        info.appName = pi.applicationInfo
                .loadLabel(context.getPackageManager()).toString();
        info.packageName = pi.packageName;
        info.versionCode = pi.versionCode;
        info.versionName = pi.versionName;
        info.firstInstallTime = getStringDate(pi.firstInstallTime);
        info.appIcon = pi.applicationInfo.loadIcon(context.getPackageManager());
        return info;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate(long date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        return dateString;
    }

}
