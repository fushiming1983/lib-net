package com.suning.baseui.log;

import android.util.Log;

/**
 * LogCatLogger，日志打印类
 *
 * @author dmh
 * @version V1.0
 * @Title ILogger
 * @Package com.pplive.androidphone.sport
 * @date 2017-06-06
 */
public class LogCatLogger implements ILogger {
    @Override
    public void d(String tag, String message) {
        Log.d(tag, message);
    }

    @Override
    public void e(String tag, String message) {
        Log.e(tag, message);
    }

    @Override
    public void i(String tag, String message) {
        Log.i(tag, message);
    }

    @Override
    public void v(String tag, String format, Object... args) {
        Log.v(tag, Logger.buildMessage(format, args));
    }

    @Override
    public void w(String tag, String format, Object... args) {
        Log.w(tag, Logger.buildMessage(format, args));
    }

    @Override
    public void d(String tag, String format, Object... args) {
        Log.d(tag, Logger.buildMessage(format, args));
    }

    @Override
    public void e(String tag, String format, Object... args) {
        Log.e(tag, Logger.buildMessage(format, args));
    }

    @Override
    public void i(String tag, String format, Object... args) {
        Log.i(tag, Logger.buildMessage(format, args));
    }

    @Override
    public void v(String tag, String message) {
        Log.v(tag, message);
    }

    @Override
    public void w(String tag, String message) {
        Log.w(tag, message);
    }

    @Override
    public void println(int priority, String tag, String message) {
        Log.println(priority, tag, message);
    }

    @Override
    public void open() {
        // TODO Auto-generated method stub

    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }
}
