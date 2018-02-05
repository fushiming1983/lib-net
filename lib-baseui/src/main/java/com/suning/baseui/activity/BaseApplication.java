package com.suning.baseui.activity;

import android.app.Application;
import android.content.Context;

import com.suning.baseui.info.ActivityManager;
import com.suning.baseui.info.AppInfo;
import com.suning.baseui.info.DeviceInfo;
import com.suning.baseui.log.Crash;
import com.suning.baseui.log.FileLiveLogger;
import com.suning.baseui.log.FileNewLogger;
import com.suning.baseui.log.LogCatLogger;
import com.suning.baseui.log.Logger;
import com.suning.baseui.log.PLogger;
import com.suning.baseui.utlis.AppUtil;
import com.suning.baseui.utlis.DensityUtil;

/**
 * BaseApplication是Application的基础类，以后将固定不变的东西放在里面，进行抽取和封装。
 * 以后可以将固定的初始化放在这里
 * 不常用的暴露出去
 *
 * @author dmh
 * @version V1.0
 * @Title ILogger
 * @Package com.pplive.androidphone.sport
 * @date 2017-06-06
 */
public abstract class BaseApplication extends Application {

    /**
     * context
     */
    public static Context mContext;

    /**
     * TAG
     */
    public final String TAG = "BaseApplication";

    /**
     * activityManager
     */
    private static ActivityManager activityManager = null;

    /**
     * 标示主进程 true 为主进程 false为非主进程。
     */
    public static boolean isMainProcess = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        //TODO 这是为了解决多进程的问题
        isMainProcess = AppUtil.isMainProcess(mContext);
        if (!isMainProcess) return;
        //开始初始化应用
        iniLog();
        initCrash();
        initDensity();
        initStatistic();
        initVideoManager();
        initNetworkManager();
        initDataBaseManager();
    }

    /**
     * 初始化自定义Activity管理器
     */
    protected void initActivity() {
        if (activityManager == null) {
            activityManager = ActivityManager.getScreenManager();
        }
    }

    public ActivityManager getActivityManager() {
        initActivity();
        return activityManager;
    }

    /**
     * 捕获闪退异常，在release版中，将日志写入本地并提交服务器，用于异常追踪
     */
    protected void initCrash() {
        Crash.getInstance().init(mContext);
    }

    /**
     * 日志的创建
     */
    private void iniLog() {
        //applog日志
        Logger.addLogger(new FileNewLogger(mContext));
        Logger.addLogger(new LogCatLogger());
        Logger.i(TAG, DeviceInfo.print(mContext));
        Logger.i(TAG, AppInfo.print(mContext));
        //直播日志（白名单）
        PLogger.addLogger(new FileLiveLogger(mContext));
        PLogger.addLogger(new LogCatLogger());
        PLogger.i(TAG, DeviceInfo.print(mContext));
        PLogger.i(TAG, AppInfo.print(mContext));
    }

    /**
     * 尺寸工具类
     */
    private void initDensity() {
        DensityUtil.init(mContext);
    }


    /**
     * 网络相关的初始化工作
     */
    protected abstract void initNetworkManager();


    /**
     * 本地数据库相关的初始化工作
     */
    protected abstract void initDataBaseManager();

    /**
     * 视频播放sdk
     */
    protected abstract void initVideoManager();

    /**
     * TODO
     * 云迹的统计，这个本来想放在base里面，但发现在lib-net里面有引用，不敢轻易动。
     * 先在这里记录一下，等缓过来，搞定。
     */
    protected abstract void initStatistic();


    /**
     * 友盟统计的初始化（有可能不需要，先这样写上去）
     */
    //protected abstract void initUmeng();


    /**
     * 剩余处理
     * 暂时就这样写，需要重新把剩余的进行梳理
     */
    //protected abstract void initOther();


}
