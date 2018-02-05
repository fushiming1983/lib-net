package com.suning.baseui.utlis;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.Log;

import com.suning.baseui.log.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * PhoneUtil，手机信息获取类
 *
 * @author dmh
 * @version V1.0
 * @Title ILogger
 * @Package com.pplive.androidphone.sport
 * @date 2017-06-06
 */
public class PhoneUtil {


    private static final String defaultDeviceID = "1";
    private static String Tag = "PhoneUtil";
    private static List<String> INVALID_DEVICEID = new ArrayList<String>();

    static {
        INVALID_DEVICEID.add("358673013795895");
        INVALID_DEVICEID.add("004999010640000");
        INVALID_DEVICEID.add("00000000000000");
        INVALID_DEVICEID.add("000000000000000");
    }

    /**
     * 获取应用版本号；
     *
     * @param context
     * @return 应用版本号，如1
     */
    public static int getVerCode(Context context) {
        int verCode = -1;
        if (context != null) {
            try {
                PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                verCode = info.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return verCode;
    }

    /**
     * 获取版本名称；
     *
     * @param context
     * @return 应用版本名称，如1.0
     */
    public static String getVerName(Context context) {
        String verName = "";
        if (context != null) {
            try {
                PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                verName = info.versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return verName;

    }

    /**
     * 获取MAC地址，注意：手机重启，mac地址为null；
     *
     * @param context
     * @return mac地址；
     */
    public static String getMac(Context context) {
        if (context != null) {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = wifi.getConnectionInfo();
            return info.getMacAddress();
        } else {
            return "";
        }
    }

    /**
     * 获取手机号码；
     *
     * @param context
     */
    public static String getPhoneNumber(Context context) {
        String number = "";
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm != null) {
            number = tm.getLine1Number();
        }
        return number;
    }

    /**
     * 获取屏幕宽度；
     */
    public static int getScreenWidth(Context context) {
        return context.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度；
     */
    public static int getScreenHeight(Context context) {
        return context.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取设备号，缺点如下；
     * <p>
     * 非手机设备：如果只带有Wifi的设备或者音乐播放器没有通话的硬件功能的话就没有这个DEVICE_ID
     * 权限：获取DEVICE_ID需要READ_PHONE_STATE权限，但如果我们只为了获取它，没有用到其他的通话功能， 那这个权限有点大才小用
     * bug：在少数的一些手机设备上，该实现有漏洞，会返回垃圾，如:zeros或者asterisks的产品
     *
     * @param context 可能返回null
     * @deprecated Implement {@link #
     * <p>
     * getDeviceUniqueID(Intent, int, int)}
     */
    public static String getDeviceID(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String uniqueID = tm.getDeviceId();
            return uniqueID;
        } catch (Exception e) {
            return defaultDeviceID;
        }
    }

    /**
     * 获取AndroidID，缺点如下：
     * <p>
     * 它在Android <=2.1 or Android >=2.3的版本是可靠、稳定的，但在2.2的版本并不是100%可靠的
     * 在主流厂商生产的设备上，有一个很经常的bug，就是每个设备都会产生相同的ANDROID_ID：9774d56d682e549c
     *
     * @param context
     */
    public static String getAndroidID(Context context) {
        String androidid = Settings.System.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidid;
    }

    public static boolean isValidDeviceid(String deviceid) {
        if (StringUtil.isEmpty(deviceid))
            return false;
        if (deviceid.length() < 10)
            return false;
        if (INVALID_DEVICEID.contains(deviceid))
            return false;
        if (isMessyCode(deviceid))
            return false;
        return true;
    }

    /* 判断字符是否是中文
    *
            * @param c 字符
    * @return 是否是中文
    */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    public static boolean isMessyCode(String strName) {
        try {
            if (StringUtil.isEmpty(strName))
                return true;
            Pattern p = Pattern.compile("\\s*|t*|r*|n*");
            Matcher m = p.matcher(strName);
            String after = m.replaceAll("");
            String temp = after.replaceAll("\\p{P}", "");
            char[] ch = temp.trim().toCharArray();
            float chLength = ch.length;
            float count = 0;
            for (int i = 0; i < ch.length; i++) {
                char c = ch[i];
                if (!Character.isLetterOrDigit(c)) {
                    if (!isChinese(c)) {
                        count = count + 1;
                    }
                }
            }
            float result = count / chLength;
            if (result > 0.4) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            Logger.e(Tag, ex.toString());
        }
        return true;
    }

    /**
     * 获取手机品牌和型号；
     *
     * @param context
     */
    public static String getBrandModel(Context context) {
        String brand = Build.BRAND + Build.MODEL;
        return brand;
    }

    /**
     * 获取手机系统版本；
     *
     * @param context
     */
    public static String getPhoneVersion(Context context) {
        String version = Build.VERSION.RELEASE;
        return version;
    }

    /**
     * 获取屏幕分辨率；
     */
    public static String getResolution(Context context) {
        return getScreenWidth(context) + "*" + getScreenHeight(context);
    }


    /**
     * 检测网络类型；平板电脑可能没有电话service; 联通3GNETWORK_TYPE_UMTS，移动3GNETWORK_TYPE_HSDPA，
     * 电信3GNETWORK_TYPE_EVDO_0NETWORK_TYPE_EVDO_A
     *
     * @param context
     * @return boolean
     */
    public static String getNetWork(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null && info.getType() == 1) {
                return "WIFI";
            }
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        // 获得手机SIMType
        if (tm != null) {
            int type = tm.getNetworkType();
            if (type == TelephonyManager.NETWORK_TYPE_UMTS || type == TelephonyManager.NETWORK_TYPE_HSDPA
                    || type == TelephonyManager.NETWORK_TYPE_EVDO_0 || type == TelephonyManager.NETWORK_TYPE_EVDO_A) {
                return "3G";
            } else if (type == TelephonyManager.NETWORK_TYPE_LTE) {
                return "4G";
            } else if (type == TelephonyManager.NETWORK_TYPE_GPRS || type == TelephonyManager.NETWORK_TYPE_EDGE
                    || type == TelephonyManager.NETWORK_TYPE_CDMA) {
                return "2G";
            }
        }
        return "未知";
    }


    /**
     * 检测网络类型；平板电脑可能没有电话service; 联通3GNETWORK_TYPE_UMTS，移动3GNETWORK_TYPE_HSDPA，
     * 电信3GNETWORK_TYPE_EVDO_0NETWORK_TYPE_EVDO_A
     *
     * @param context
     * @return boolean
     */
    public static int getNetWork_PlayLog(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info != null && info.getType() == 1) {
                return /*"WIFI"*/2;
            }
        }
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        // 获得手机SIMType
        if (tm != null) {
            int type = tm.getNetworkType();
            if (type == TelephonyManager.NETWORK_TYPE_UMTS || type == TelephonyManager.NETWORK_TYPE_HSDPA
                    || type == TelephonyManager.NETWORK_TYPE_EVDO_0 || type == TelephonyManager.NETWORK_TYPE_EVDO_A) {
                return /*"3G"*/4;
            } else if (type == TelephonyManager.NETWORK_TYPE_LTE) {
                return /*"4G"*/5;
            } else if (type == TelephonyManager.NETWORK_TYPE_GPRS || type == TelephonyManager.NETWORK_TYPE_EDGE
                    || type == TelephonyManager.NETWORK_TYPE_CDMA) {
                return /*"2G"*/3;
            }
        }
        return /*"未知"*/ 0;
    }


    /**
     * 获取operator：运营商名称；
     *
     * @param context
     * @return 运营商名称
     */
    public static String getOperator(Context context) {
        TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String operator = telManager.getSimOperator();

        if (operator != null) {

            if (operator.equals("46000") || operator.equals("46002") || operator.equals("46007")) {

                // 中国移动
                return "中国移动";

            } else if (operator.equals("46001")) {

                // 中国联通
                return "中国联通";
            } else if (operator.equals("46003")) {

                // 中国电信
                return "中国电信";
            }
        }
        return "未知";

    }


    /**
     * 获取operator：运营商名称；
     *
     * @param context
     * @return 运营商名称
     */
    public static int getOperator_PlayLog(Context context) {
        TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String operator = telManager.getSimOperator();

        if (operator != null) {

            if (operator.equals("46000") || operator.equals("46002") || operator.equals("46007")) {

                // 中国移动
                return /*"中国移动"*/1;

            } else if (operator.equals("46001")) {

                // 中国联通
                return /*"中国联通"*/2;
            } else if (operator.equals("46003")) {

                // 中国电信
                return /*"中国电信"*/3;
            }
        }
        return /*"未知"*/-1;

    }


    /**
     * 获取包名；
     *
     * @param context
     * @return channelID
     */
    public static String getPacakgeName(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String pn = pi.packageName;
            return pn;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 当前Android系统版本是否在（ Donut） Android 1.6或以上
     *
     * @return
     */
    public static boolean hasDonut() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT;
    }

    /**
     * 当前Android系统版本是否在（ Eclair） Android 2.0或 以上
     *
     * @return
     */
    public static boolean hasEclair() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR;
    }

    /**
     * 当前Android系统版本是否在（ Froyo） Android 2.2或 Android 2.2以上
     *
     * @return
     */
    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * 当前Android系统版本是否在（ Gingerbread） Android 2.3x或 Android 2.3x 以上
     *
     * @return
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * 当前Android系统版本是否在（ Honeycomb） Android3.1或 Android3.1以上
     *
     * @return
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * 当前Android系统版本是否在（ HoneycombMR1） Android3.1.1或 Android3.1.1以上
     *
     * @return
     */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * 当前Android系统版本是否在（ IceCreamSandwich） Android4.0或 Android4.0以上
     *
     * @return
     */
    public static boolean hasIcecreamsandwich() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }

    /**
     * 当前Android系统版本是否在（ JELLY_BEAN_MR1） Android4.2或 Android4.2以上
     *
     * @return
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    /**
     * 当前Android系统版本是否在（ KITKAT） Android4.4或 Android4.4以上
     * Build.VERSION_CODES.KITKAT=19
     *
     * @return
     */
    public static boolean hasKitkat() {
        return Build.VERSION.SDK_INT >= 19;
    }

    /**
     * 当前Android系统版本是否（ KITKAT） Android4.4 Build.VERSION_CODES.KITKAT=19
     *
     * @return
     */
    public static boolean isKitkat() {
        return Build.VERSION.SDK_INT == 19;
    }

    public static boolean isDroidXDroid2() {
        return (Build.MODEL.trim().equalsIgnoreCase("DROIDX") || Build.MODEL.trim().equalsIgnoreCase("DROID2")
                || Build.FINGERPRINT.toLowerCase().contains("shadow") || Build.FINGERPRINT.toLowerCase().contains("droid2"));
    }

    /**
     * Get the memory class of this device (approx. per-app memory limit)
     *
     * @param context
     * @return
     */
    public static int getMemoryClass(Context context) {
        return ((android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
    }

    /**
     * 获取CPU名字
     *
     * @param @return
     * @return String
     */
    public static String getCpuName() {
        try {
            FileReader fr = new FileReader("/proc/cpuinfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split(":\\s+", 2);
            for (int i = 0; i < array.length; i++) {
            }
            br.close();
            return array[1];
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 验证手机号码是否正确;
     *
     * @param number 手机号码
     * @return true为合法，否则非法；
     */
    public static boolean checkPhoneNumber(String number) {
        if (number.length() == 11
                && (number.startsWith("13") || number.startsWith("14") || number.startsWith("15") || number.startsWith("16")
                || number.startsWith("17") || number.startsWith("18"))) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 获取当前应用可用内存大小 string
     * @param context
     * @return
     */
    public static String getAvailMemory(Context context) {

        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        //mi.availMem; 当前系统的可用内存
        return Formatter.formatFileSize(context, mi.availMem);// 将获取的内存大小规格化
    }

    /**
     * 获取当前应用可用内存大小 string
     * @param context
     * @return
     */
    public static int getAvailMemory_PlayLog(Context context) {

        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        //mi.availMem; 当前系统的可用内存
        return new Long(mi.availMem).intValue()/1024;// 将获取的内存大小规格化
    }

    /**
     * 获取当前手机内存大小
     * @param context
     * @return
     */
    public static String getTotalMemory(Context context) {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "\t");
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();

        } catch (IOException e) {
        }
        return Formatter.formatFileSize(context, initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }


    /**
     * 获取当前手机内存大小
     * @param context
     * @return
     */
    public static double getTotalMemory_PlayLog(Context context) {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        double initial_memory = 0;
        double memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "\t");
            }

            initial_memory = Double.valueOf(arrayOfString[1]).intValue()/(double)1024/(double)1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();
            memory =  new BigDecimal(initial_memory).setScale(2, RoundingMode.UP).doubleValue();
        } catch (IOException e) {
            return 0;
        }
        return  memory;// Byte转换为KB或者MB，内存大小规格化
    }

}
