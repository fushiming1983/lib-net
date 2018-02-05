package com.suning.baseui.log;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

import com.suning.baseui.utlis.AppUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by suning on 2017/6/7.
 */

public class Crash implements Thread.UncaughtExceptionHandler {

    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler defaultHandler;

    // CrashHandler实例
    private static Crash INSTANCE;

    // 程序的Context对象
    private Context context;

    private static final String TAG = "Crash";

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Logger.e(TAG, "捕获全局异常");
        if (!handleException(ex) && defaultHandler != null) {
            // 如果用户没有处理则让系统默认的异常处理器来处理
            defaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            //程序崩溃后 Kill本进程
            AppUtil.closeApp(context);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    /** 获取CrashHandler实例 ,单例模式 */
    public static Crash getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Crash();
        }
        return INSTANCE;
    }

    /**
     * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
     *
     * @param context
     *            上下文
     *
     */
    public void init(Context context) {
        this.context = context;
        defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        Logger.e(TAG, "开启异常捕获-----------");
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }
        // 发送错误消息至服务器；
        final Throwable throwable = ex;
        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(context, "捕获全局异常，程序即将关闭！", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();
        saveCrash(ex);
        return true;
    }

    /**
     * 获取异常信息
     * @param throwable
     * @return
     */
    private String getExceptionMessage(Throwable throwable){
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        Throwable cause = throwable.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        return writer.toString();
    }


    //用于格式化日期,作为日志文件名的一部分
//    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    public static String mPath = "/mnt/sdcard/crash/";

    /**
     * 保存日志；
     *
     * @param e
     */
    private void saveCrash(Throwable e) {
        StringBuffer sb = new StringBuffer();
        Writer info = new StringWriter();
        PrintWriter printWriter = new PrintWriter(info);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        sb.append(info.toString());
        printWriter.close();
        Logger.e(TAG, "程序崩溃，日志=" + sb.toString());


//        String sdDir = context.getExternalCacheDir().getAbsolutePath();
//        scanFileAsync(context,sdDir);

//        try {
//            String time = formatter.format(new Date());
//            String fileName = "crash-" + time+ ".log";
//            File dir = new File(mPath);
//            if (!dir.exists()) {
//                dir.mkdirs();
//            }
//            FileOutputStream fos = new FileOutputStream(mPath + fileName);
//            fos.write(sb.toString().getBytes());
//            fos.close();
//        } catch (Exception exception) {
//            Log.e(TAG, "an error occured while writing file...", exception);
//        }

    }


//    public void scanFileAsync(Context ctx, String filePath) {
//        Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        scanIntent.setData(Uri.fromFile(new File(filePath)));
//        ctx.sendBroadcast(scanIntent);
//    }

}
