package com.suning.baseui.utlis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by suning on 2017/6/26.
 */

public class DateUtil {

    private static final String TAG = "AppUtil";


    /**
     * 获取手机系统时间
     */
    public static String getSystemTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取手机系统时间 有毫秒值
     */
    public static String getSystemTimes() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }


    /**
     * 传入上次的时间和现在 的时间进行比对
     * @param oldTime
     * @param compare
     * @return
     */
    public static boolean compareTime(String oldTime, long compare) {
        boolean flag = false;
        if (StringUtil.isEmpty(oldTime)) return true;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = new Date(System.currentTimeMillis());//获取当前时间
            Date date2 = sf.parse(oldTime);
            flag = date1.getTime() - date2.getTime() > compare;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;

    }

    /**
     * Desc： 毫秒值时间
     */
    public static long getCaseMsecTimes(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date();
        //获取当前时间
        try {
            curDate = formatter.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return curDate.getTime();
    }

    /**
     * Desc ：年月日
     */
    public static String getCaseStringTimes(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(time);
        String stime = formatter.format(curDate);
        return stime;
    }

    /**
     * 用于服务端返回时间的更改
     * @param time
     * @return
     */
    public static String getCaseStringTimess(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date curDate = new Date(time);
        String stime = formatter.format(curDate);
        return stime;
    }


    /**
     * 服务器时间与本地时间对比
     *
     * @param serverTime   获取服务器的时间
     * @param distanceTime 时间误差值范围绝对值
     * @return
     */
    public static boolean compareStime(String serverTime, long distanceTime) {
        boolean flag = false;
        if (StringUtil.isEmpty(serverTime)) return true;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date1 = new Date(System.currentTimeMillis());//获取当前时间
            Date date2 = sf.parse(serverTime);
            if (Math.abs(date1.getTime() - date2.getTime()) < distanceTime) {
                flag = true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;

    }


}
