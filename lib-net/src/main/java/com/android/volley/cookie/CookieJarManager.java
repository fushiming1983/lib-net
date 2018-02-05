/*
 * Copyright (C), 2002-2016, �����׹������������޹�˾
 * FileName: CookieStoreManager.java
 * Author:   14074533
 * Date:     2016-3-30 ����9:26:51
 * Description: //ģ��Ŀ�ġ���������      
 * History: //�޸ļ�¼
 * <author>      <time>      <version>    <desc>
 * �޸�������             �޸�ʱ��            �汾��                  ����
 */
package com.android.volley.cookie;

import android.app.Application;
import android.content.Context;

/**
 * Cookie管理
 *
 * @author 14074533
 */
public class CookieJarManager {

    private static volatile CookieJarManager instance;

    private static Application mApplication;

    private static CookieJarImpl cookieJar;
    /**
     * 风控设备指纹
     */
    public final String DEVICE_ID = "_device_session_id";

    private CookieJarManager() {
    }

    public static CookieJarManager getInstance() {
        if (instance == null) {
            synchronized (CookieJarManager.class) {
                if (instance == null) {
                    instance = new CookieJarManager();
                }
            }
        }
        return instance;
    }

    public static void init(Application app) {
        mApplication = app;
        cookieJar = new CookieJarImpl(new PersistentCookieStore());
    }

    public static Context getContext() {
        return mApplication;
    }

    /**
     * 获取全局的cookie实例
     */
    public static CookieJarImpl getCookieJar() {
        return cookieJar;
    }

    /**
     * 清除
     */
    public void clear() {
        clearCookieStore();
        if (instance != null) {
            mApplication = null;
            cookieJar = null;
            instance = null;
        }
    }

    /**
     * 清空Cookie
     */
    public void clearCookieStore() {
        cookieJar.getCookieStore().removeAllCookie();
    }
}
