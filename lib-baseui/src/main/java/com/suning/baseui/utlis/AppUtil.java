package com.suning.baseui.utlis;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.suning.baseui.activity.BaseApplication;
import com.suning.baseui.log.Logger;

import java.util.List;


/**
 * AppUtil，整个应用的工具类
 *
 * @author dmh
 * @version V1.0
 * @Title ILogger
 * @Package com.pplive.androidphone.sport
 * @date 2017-06-06
 */
public class AppUtil {

    private static final String TAG = "AppUtil";

    /**
     * 关闭整个应用程序
     *
     * @param: @param activity
     * @return: void
     */
    public static void closeApp(Context context) {
        BaseApplication application = (BaseApplication) context
                .getApplicationContext();
        application.getActivityManager().popAllActivity();
        Logger.i(TAG, "已关闭APP");
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用的进程
     * @param context
     * @return
     */
    public static String getProcessName(Context context) {

        ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo proInfo : runningApps) {
            if (proInfo.pid == android.os.Process.myPid()) {
                if (proInfo.processName != null) {
                    return proInfo.processName;
                }
            }
        }
        return null;
    }

    /**
     * 获取应用的进程
     * @param context
     * @return
     */
    public static boolean isMainProcess(Context context) {
        int myPid = android.os.Process.myPid();
        android.app.ActivityManager manager
                = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        for (android.app.ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == myPid) {
                return context.getPackageName().equals(processInfo.processName);
            }
        }
        return true;
    }

}
