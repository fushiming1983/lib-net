package com.suning.baseui.log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * PliveLogger是一个日志打印类
 *
 * @version V1.0
 * @Title ILogger
 * @Package com.pplive.androidphone.sport
 */
public class PLogger extends Logger {
    /**
     * Priority constant for the println method; use TALogger.v.
     */
    public static final int VERBOSE = 2;

    /**
     * Priority constant for the println method; use TALogger.d.
     */
    public static final int DEBUG = 3;

    /**
     * Priority constant for the println method; use TALogger.i.
     */
    public static final int INFO = 4;

    /**
     * Priority constant for the println method; use TALogger.w.
     */
    public static final int WARN = 5;

    /**
     * Priority constant for the println method; use TALogger.e.
     */
    public static final int ERROR = 6;
    /**
     * Priority constant for the println method.
     */
    public static final int ASSERT = 7;

    /**
     * 是否为调试模式
     */
    public static boolean isDebug = true;

    private static HashMap<String, ILogger> loggerHashMap = new HashMap<String, ILogger>();

    // private static final ILogger defaultLogger = new LogCatLogger();

    public static void addLogger(ILogger logger) {
        String loggerName = logger.getClass().getName();
        // String defaultLoggerName = defaultLogger.getClass().getName();
        if (!loggerHashMap.containsKey(loggerName)) {
            logger.open();
            loggerHashMap.put(loggerName, logger);
        }

    }

    public static void removeLogger(ILogger logger) {
        String loggerName = logger.getClass().getName();
        if (loggerHashMap.containsKey(loggerName)) {
            logger.close();
            loggerHashMap.remove(loggerName);
        }

    }

    /**
     * 输出调试消息
     *
     * @param @param object
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void d(Object object, String message) {
        if (isDebug) {
            printLoger(DEBUG, object, message);
        }
    }

    /**
     * 输出错误消息
     *
     * @param @param object
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void e(Object object, String message) {
        if (isDebug) {
            printLoger(ERROR, object, message);
        }
    }

    /**
     * 输出正常信息
     *
     * @param @param object
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void i(Object object, String message) {
        if (isDebug) {
            printLoger(INFO, object, message);
        }
    }

    /**
     * 输出所有信息
     *
     * @param @param object
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void v(Object object, String message) {
        if (isDebug) {
            printLoger(VERBOSE, object, message);
        }
    }

    /**
     * 输出警告信息
     *
     * @param @param object
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void w(Object object, String message) {
        if (isDebug) {
            printLoger(WARN, object, message);
        }
    }

    /**
     * 输出调试信息
     *
     * @param @param tag
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void d(String tag, String format, Object... args) {
        if (isDebug) {
            printLoger(DEBUG, tag, format, args);
        }
    }

    /**
     * 输出调试信息
     *
     * @param @param tag
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void d(String tag, String message) {
        if (isDebug) {
            printLoger(DEBUG, tag, message);
        }
    }

    /**
     * 输出异常信息
     *
     * @param @param tag
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void e(String tag, String message) {
        if (isDebug) {
            printLoger(ERROR, tag, message);
        }
    }

    /**
     * 输出异常信息
     *
     * @param @param tag
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void e(String tag, String format, Object... args) {
        if (isDebug) {
            printLoger(ERROR, tag, format, args);
        }
    }

    /**
     * 输出正常信息
     *
     * @param @param tag
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void i(String tag, String message) {
        if (isDebug) {
            printLoger(INFO, tag, message);
        }
    }

    /**
     * 输出正常信息
     *
     * @param @param tag
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void i(String tag, String format, Object... args) {
        if (isDebug) {
            printLoger(INFO, tag, format, args);
        }
    }

    /**
     * 输出所有信息
     *
     * @param @param tag
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void v(String tag, String message) {
        if (isDebug) {
            printLoger(VERBOSE, tag, message);
        }
    }

    /**
     * 输出所有信息
     *
     * @param @param tag
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void v(String tag, String format, Object... args) {
        if (isDebug) {
            printLoger(VERBOSE, tag, format, args);
        }
    }

    /**
     * 输出警告信息
     *
     * @param @param tag
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void w(String tag, String message) {
        if (isDebug) {
            printLoger(WARN, tag, message);
        }

    }

    /**
     * 输出警告信息
     *
     * @param @param tag
     * @param @param message
     * @return void
     */
    @SuppressWarnings("unused")
    public static void w(String tag, String format, Object... args) {
        if (isDebug) {
            printLoger(WARN, tag, format, args);
        }

    }

    public static void println(int priority, String tag, String message) {
        printLoger(priority, tag, message);
    }

    private static void printLoger(int priority, Object object, String message) {
        Class<?> cls = object.getClass();
        String tag = cls.getName();
        String arrays[] = tag.split("\\.");
        tag = arrays[arrays.length - 1];
        printLoger(priority, tag, message);
    }

    @SuppressWarnings("unused")
    private static void printLoger(int priority, String tag, String message) {
        if (isDebug) {
            // printLoger(defaultLogger, priority, tag, message);
            Iterator<Map.Entry<String, ILogger>> iter = loggerHashMap.entrySet()
                    .iterator();
            while (iter.hasNext()) {
                Map.Entry<String, ILogger> entry = iter.next();
                ILogger logger = entry.getValue();
                if (logger != null) {
                    printLoger(logger, priority, tag, message);
                }
            }
        }
    }

    @SuppressWarnings("unused")
    private static void printLoger(int priority, String tag, String format,
                                   Object... args) {
        if (isDebug) {
            // printLoger(defaultLogger, priority, tag, message);
            Iterator<Map.Entry<String, ILogger>> iter = loggerHashMap.entrySet()
                    .iterator();
            while (iter.hasNext()) {
                Map.Entry<String, ILogger> entry = iter.next();
                ILogger logger = entry.getValue();
                if (logger != null) {
                    printLoger(logger, priority, tag, format, args);
                }
            }
        }
    }

    private static void printLoger(ILogger logger, int priority, String tag,
                                   String message) {

        switch (priority) {
            case VERBOSE:
                logger.v(tag, message);
                break;
            case DEBUG:
                logger.d(tag, message);
                break;
            case INFO:
                logger.i(tag, message);
                break;
            case WARN:
                logger.w(tag, message);
                break;
            case ERROR:
                logger.e(tag, message);
                break;
            default:
                break;
        }
    }

    private static void printLoger(ILogger logger, int priority, String tag,
                                   String format, Object... args) {

        switch (priority) {
            case VERBOSE:
                logger.v(tag, format, args);
                break;
            case DEBUG:
                logger.d(tag, format, args);
                break;
            case INFO:
                logger.i(tag, format, args);
                break;
            case WARN:
                logger.w(tag, format, args);
                break;
            case ERROR:
                logger.e(tag, format, args);
                break;
            default:
                break;
        }
    }

    public static String buildMessage(String format, Object... args) {
        try {
            String msg = (args == null) ? format : String.format(Locale.US,
                    format, args);
            StackTraceElement[] trace = new Throwable().fillInStackTrace()
                    .getStackTrace();

            String caller = "<unknown>";
            // Walk up the stack looking for the first caller outside of Logger.
            // It will be at least two frames up, so start there.
            for (int i = 2; i < trace.length; i++) {
                Class<?> clazz = trace[i].getClass();
                if (!clazz.equals(Logger.class)) {
                    String callingClass = trace[i].getClassName();
                    callingClass = callingClass.substring(callingClass
                            .lastIndexOf('.') + 1);
                    callingClass = callingClass.substring(callingClass
                            .lastIndexOf('$') + 1);

                    caller = callingClass + "." + trace[i].getMethodName();
                    break;
                }
            }
            return String.format(Locale.US, "[%d] %s: %s", Thread
                    .currentThread().getId(), caller, msg);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}

