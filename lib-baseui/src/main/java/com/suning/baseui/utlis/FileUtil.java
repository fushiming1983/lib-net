package com.suning.baseui.utlis;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileUtil，文件工具类
 *
 * @author dmh
 * @version V1.0
 * @Title ILogger
 * @Package com.pplive.androidphone.sport
 * @date 2017-06-06
 */
public class FileUtil {


    private static final ReentrantLock logFileLock = new ReentrantLock();

    /**
     * 创建文件夹，支持创建多级目录；
     *
     * @param path 路径名称
     * @return boolean 是否创建成功
     */
    public static boolean createFolder(String path) {
        if (StringUtil.isNotNull(path) && !"null".equals(path.substring(path.lastIndexOf("/") + 1))) {
            File file = new File(path);
            if (!file.exists()) {
                // 创建多级目录；
                return file.mkdirs();
            }
        }
        return false;
    }

    /**
     * 创建文件；
     *
     * @param path 路径名称
     * @return boolean 是否创建成功
     * @throws IOException
     */
    public static boolean createFile(String path) throws IOException {
        if (StringUtil.isNotNull(path)) {
            File file = new File(path);
            if (!file.exists()) {
                // 如果不存在，创建新文件；
                return file.createNewFile();
            }
        }
        return false;
    }

    /**
     * 判断路径是否存在，如果不存在在，则创建；
     *
     * @param path 路径名称
     * @return boolean 是否存在，或无法创建
     */
    public static boolean hasFolder(String path) {
        if (StringUtil.isNotNull(path)) {
            File file = new File(path);
            if (!file.exists()) {
                // 创建多级目录；
                return file.mkdirs();
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     *   文件夹是否存在并文件夹不为空
     * @param path
     * @return
     */
    public static boolean isExist(String path) {
        if (StringUtil.isNotNull(path)) {
            File file = new File(path);
            if (file.exists() && file.isDirectory()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除文件
     *
     * @param path
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            if (file.isFile()) {// 删除文件前重命名。
                // 可能是系统bug，见http://stackoverflow.com/questions/11539657/open-failed-ebusy-device-or-resource-busy
                File to = new File(file.getAbsolutePath() + System.currentTimeMillis());
                file.renameTo(to);
                to.delete();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                int len = files == null ? 0 : files.length;
                for (int i = 0; i < len; i++) {
                    deleteFile(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }

    /**
     * 删除文件的根目录的所有文件
     *
     * @param path
     */
    public static void deleteParentFile(String path) {
        File file = new File(path).getParentFile();
        if (file.exists()) {
            if (file.isFile()) {// 删除文件前重命名。
                // 可能是系统bug，见http://stackoverflow.com/questions/11539657/open-failed-ebusy-device-or-resource-busy
                File to = new File(file.getAbsolutePath() + System.currentTimeMillis());
                file.renameTo(to);
                to.delete();
            } else if (file.isDirectory()) {
                File[] files = file.listFiles();
                int len = files == null ? 0 : files.length;
                for (int i = 0; i < len; i++) {
                    deleteParentFile(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     */
    public static void deleteAllFilesOfDir(String str) {

        File path = new File(str);
        if (!path.exists())
            return;
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            deleteAllFilesOfDir(files[i].getAbsolutePath());
        }
        path.delete();
    }

    /**
     * 过滤文件;
     *
     * @param extension 后缀
     * @return 根据后缀过滤文件
     */
    public static FilenameFilter getFileExtensionFilter(String extension) {
        final String _extension = extension;
        return new FilenameFilter() {
            public boolean accept(File file, String name) {
                return name.endsWith(_extension);
            }
        };
    }

    /**
     * @Title: resetFile
     * @Description: TODO(恢复音视频文件)
     * @param: @param path
     * @return: void
     */
    public static boolean resetFile(String path) {
        // 将完整文件的开头1024清空；
        boolean isOK = false;
        if (path != null && !"".equals(path)) {
            RandomAccessFile raf = null;
            File file = null;
            try {
                file = new File(path);
                if (file.exists() && file.canWrite() && file.isFile()) {
                    raf = new RandomAccessFile(file, "rw");
                    raf.seek(0);
                    raf.write(new byte[1024]);
                    raf.close();
                    path = null;
                    isOK = true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isOK;
    }

    /**
     * 重命名，需要是文件的具体路径。比如/sdcard/cdel/target.txt
     *
     * @Title: renameFile
     * @param: @param path 需要命名的文件
     * @param: @param filePath 修改后的文件名
     * @return: void
     */
    public static boolean renameFile(String source, String target) {
        if (StringUtil.isNotNull(source) && StringUtil.isNotNull(target)) {
            File oldF = new File(source);
            File newF = new File(target);
            return oldF.renameTo(newF);
        }
        return false;
    }


    /**
     * @Title: chmod
     * @Description: 修改文件权限
     * @param: @param permission
     * @param: @param path
     * @return: void
     */
    public static void chmod(String permission, String path) {
        try {
            String command = "chmod " + permission + " " + path;
            Runtime runtime = Runtime.getRuntime();
            runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 扫描文件
     *
     * @param @param context
     * @param @param file
     * @return void
     * @Title scanFile
     */
    public static void scanFile(Context context, String file) {
        if (context != null && StringUtil.isNotNull(file)) {
            try {
                Uri data = Uri.parse("file://" + file);
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, data));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 拷贝文件
     *
     * @param @param  fromFile
     * @param @param  toFile
     * @param @return
     * @return boolean
     */
    public static boolean copyFile(File fromFile, File toFile) {
        try {
            InputStream is = new FileInputStream(fromFile);
            FileOutputStream fos = new FileOutputStream(toFile);
            byte[] buffer = new byte[7168];
            int count = 0;
            while ((count = is.read(buffer)) > 0) {
                fos.write(buffer, 0, count);
            }
            fos.close();
            is.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    final static String ENVIROMENT_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();
    /**
     * 日志目录
     **/
    final static String DEFAULT_LOG_DIR = "/cdel/logs/";

    /**
     * 日志文件名称
     **/
    final static String DEFAULT_LOG_FILE_NAME = "log.txt";

    public static File log(String line) {
        OutputStream out = null;
        logFileLock.lock();
        try {
            File dir = new File(ENVIROMENT_DIR + DEFAULT_LOG_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (dir.exists()) {
                File log = new File(dir, DEFAULT_LOG_FILE_NAME);
                out = new FileOutputStream(log, true);
                out.write(line.getBytes("utf-8"));
                out.flush();
                return log;
            }

        } catch (IOException e) {

        } catch (Throwable e) {

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception ex) {

                }
                out = null;
            }
            logFileLock.unlock();
        }
        return null;
    }


}
