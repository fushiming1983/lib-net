package com.suning.baseui.utlis;

/**
 * @author dmh
 * @Description: String字符串的判断
 * @date 2017/5/26
 * @Version 1.0
 */
public class StringUtil {

    /**
     * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
     * @author dmh
     * @param input
     * @return boolean 若输入字符串为null或空字符串，返回true
     */
    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否为空
     *
     * @return true：不为空， false：为空
     */
    public static boolean isNotNull(String str) {
        if (str != null && !"".equals(str)) {
            return true;
        }
        return false;
    }

}
