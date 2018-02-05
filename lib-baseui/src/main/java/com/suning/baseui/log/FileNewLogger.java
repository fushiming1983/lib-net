package com.suning.baseui.log;

import android.content.Context;
import android.os.Build;

import com.suning.baseui.utlis.AppUtil;
import com.suning.baseui.utlis.FileUtil;
import com.suning.baseui.utlis.SDCardUtil;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * FileNewLogger，文件日志打印类
 *
 * @author dmh
 * @version V1.0
 * @Title ILogger
 * @Package com.pplive.androidphone.sport
 * @date 2017-06-06
 */
public class FileNewLogger implements ILogger {

    public static final String fileName = "app_log";

    public static final int VERBOSE = 2;

    public static final int DEBUG = 3;

    public static final int INFO = 4;

    public static final int WARN = 5;

    public static final int ERROR = 6;

    public static final int ASSERT = 7;

    private String mPath;

    private FileWriter writer = null;

    private String packageName;

    private String logPath;

    private Context context;

    private File file;

    private String msg = new String();

    private Calendar calendar;

    private static final SimpleDateFormat TIMESTAMP_FMT = new SimpleDateFormat(
            "[yyyy-MM-dd HH:mm:ss] ");

    // 日志文件最大值，100K
    private static final long LOG_FILE_MAX_SIZE = 1024 * 1024 * 5;

    public FileNewLogger(Context context) {
        this.context = context;
        calendar = Calendar.getInstance();
    }


    public void open() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD && SDCardUtil.detectAvailable()) {
//            String sdDir = Environment.getExternalStorageDirectory()
//                    .getAbsolutePath();

//            packageName = AppUtil.getPackageInfo(context).packageName;

            if (context!=null && context.getExternalCacheDir() != null) {

                String sdDir = context.getExternalCacheDir().getAbsolutePath();

                packageName = AppUtil.getPackageInfo(context).packageName;

                if (!SDCardUtil.detectStorage(sdDir)) {
                    return;
                }
                try {
                    logPath = sdDir + File.separator + fileName;
                    file = new File(logPath + ".log");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    if (file.length() < LOG_FILE_MAX_SIZE) {
                        mPath = file.getAbsolutePath();
                        writer = new FileWriter(mPath, true);
                        System.out.println("已创建并打开日志文件");
                    } else {
                        FileUtil.deleteFile(logPath + ".log");
                        open();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getPath() {
        return mPath;
    }

    @Override
    public void d(String tag, String message) {
        println(DEBUG, tag, message);
    }

    @Override
    public void e(String tag, String message) {
        println(ERROR, tag, message);
    }

    @Override
    public void i(String tag, String message) {
        println(INFO, tag, message);
    }

    @Override
    public void v(String tag, String message) {
        println(VERBOSE, tag, message);
    }

    @Override
    public void w(String tag, String message) {
        println(WARN, tag, message);
    }

    @Override
    public void v(String tag, String format, Object... args) {
        println(VERBOSE, tag, Logger.buildMessage(format, args));
    }

    @Override
    public void w(String tag, String format, Object... args) {
        println(WARN, tag, Logger.buildMessage(format, args));
    }

    @Override
    public void d(String tag, String format, Object... args) {
        println(DEBUG, tag, Logger.buildMessage(format, args));
    }

    @Override
    public void e(String tag, String format, Object... args) {
        println(ERROR, tag, Logger.buildMessage(format, args));
    }

    @Override
    public void i(String tag, String format, Object... args) {
        println(INFO, tag, Logger.buildMessage(format, args));
    }

    @Override
    public void println(int priority, String tag, String message) {
        String printMessage = "";
        switch (priority) {
            case VERBOSE:
                printMessage = "[V]|" + tag + "|" + packageName + "|" + message;
                break;
            case DEBUG:
                printMessage = "[D]|" + tag + "|" + packageName + "|" + message;
                break;
            case INFO:
                printMessage = "[I]|" + tag + "|" + packageName + "|" + message;
                break;
            case WARN:
                printMessage = "[W]|" + tag + "|" + packageName + "|" + message;
                break;
            case ERROR:
                printMessage = "[E]|" + tag + "|" + packageName + "|" + message;
                break;
            default:

                break;
        }

        println(printMessage);
    }

    public void println(String message) {
        if (writer != null) {
            try {
                if (file.length() < LOG_FILE_MAX_SIZE) {
//					System.out.println("" + file.length() + "/n" + String.valueOf(LOG_FILE_MAX_SIZE) + "/n");
                    writer.write(TIMESTAMP_FMT.format(new Date()));
                    writer.write(message);
                    writer.write("\r\n");
                    writer.flush();
                } else {
//					System.out.println("dededed" +"/n" + String.valueOf(LOG_FILE_MAX_SIZE) + "/n");
                    FileUtil.deleteFile(logPath + ".log");
                    open();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void close() {
        if (writer != null) {
            try {
                writer.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
