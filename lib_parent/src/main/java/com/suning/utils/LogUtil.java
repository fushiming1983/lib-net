package com.suning.utils;

import android.util.Log;

/**
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * Author : 13120678
 * Date   : 17/3/30 下午4:33
 * Desc   : 日志打印工具
 */
public class LogUtil {

    /**
     * 日志开关变量 默认是true
     */
    public static boolean enableLog = true;


    public static void verbose(String tag, String text) {
        if (enableLog) {
            Log.v(tag, text);
        }
    }

    public static void debug(Object obj, String text) {
        if (enableLog) {
            if (obj != null) {
                debug(obj.getClass().getSimpleName(), text);
            }
        }
    }

    public static void debug(String tag, String text) {
        if (enableLog) {
            Log.d(tag, text);
        }
    }

    public static void info(String tag, String text) {
        if (enableLog) {
            Log.i(tag, text);
        }

    }

    public static void warn(String tag, String text) {
        if (enableLog) {
            Log.w(tag, text);
        }
    }

    public static void warn(String tag, String text, Throwable throwable) {
        if (enableLog) {
            Log.w(tag, text, throwable);
        }
    }

    public static void error(String tag, String text) {
        if (enableLog) {
            Log.e(tag,
                    text);
        }
    }

    public static void error(String tag, String text, Throwable throwable) {
        if (enableLog) {
            Log.e(tag, text, throwable);
        }
    }

}
