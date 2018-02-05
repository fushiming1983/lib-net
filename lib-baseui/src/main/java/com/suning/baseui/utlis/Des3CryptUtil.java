package com.suning.baseui.utlis;


import com.suning.baseui.utlis.threedes.ThreeDESUtil;

/**
 * Created by 17070687 on 2017/8/18.
 * <p>
 * 对PPTV用户信息3DES编解码
 */

public class Des3CryptUtil {


    static String deskey = "29028A7698EF4C6D3D252F02F4F79D5815389DF18525D326";
    static String hexiv = "70706C6976656F6B";


    //3DES解码
    public static String getDecryptString(String response) {

        if (response != null && response.length() == 0) {
            return null;
        }
        try {
            return ThreeDESUtil.Decrypt(response, deskey, hexiv);
        } catch (Exception e) {
            return null;
        }
    }


    //3DES编码
    public static String getEncryptString(String response) {

        if (response != null && response.length() == 0) {
            return null;
        }

        try {
            return ThreeDESUtil.Encrypt(response, deskey, hexiv);
        } catch (Exception e) {
            return null;
        }
    }

}
