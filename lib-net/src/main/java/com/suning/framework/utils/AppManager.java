package com.suning.framework.utils;

import java.util.HashMap;

/**
 * Author : 17073226
 * Date   :2017/8/31 14:21
 * Desc   :提供app的版本信息
 */

public class AppManager {

    private static AppManager mInstance;
    public static final String VERSIONNAME = "VERSIONNAME";
    public static final String VERSIONCODE = "VERSIONCODE";
    public static final String PACKAGENAME = "PACKAGENAME";
    /**
     * app的版本号
     */
    private int versionCode;
    /**
     * app的版本名
     */
    private String versionName;
    private String packageName;

    public static synchronized AppManager getInstance() {
        if (mInstance == null) {
            mInstance = new AppManager();
        }

        return mInstance;
    }

    public void init(HashMap<String, Object> params) {
        try {
            versionCode = (int) params.get(VERSIONCODE);
            versionName = (String) params.get(VERSIONNAME);
            packageName = (String) params.get(PACKAGENAME);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public String getPackageName() {
        return packageName;
    }
}
