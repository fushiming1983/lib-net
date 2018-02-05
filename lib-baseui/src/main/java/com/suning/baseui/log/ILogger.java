package com.suning.baseui.log;

/**
 * ILogger是一个日志的接口
 *
 * @author dmh
 * @version V1.0
 * @Title ILogger
 * @Package com.pplive.androidphone.sport
 * @date 2017-06-06
 */
public interface ILogger {

    void v(String tag, String message);

    void d(String tag, String message);

    void i(String tag, String message);

    void w(String tag, String message);

    void e(String tag, String message);

    void v(String tag, String format, Object... args);

    void d(String tag, String format, Object... args);

    void i(String tag, String format, Object... args);

    void w(String tag, String format, Object... args);

    void e(String tag, String format, Object... args);

    void open();

    void close();

    void println(int priority, String tag, String message);
}
