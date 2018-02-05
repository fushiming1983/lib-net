package com.suning.framework.utils;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.annotation.IgnoreExclusionStrategy;
import com.android.volley.pojos.params.IParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 * @author 13120678
 *         <p/>
 *         工具类
 */
public class FUtils {

    private static final String TAG = FUtils.class.getSimpleName();

    /**
     * 将params对象中的属性转换成参数，不包括Action地址
     *
     * @param params
     * @return
     */
    public static StringBuilder params2UrlExludeAction(String hostUrl, IParams params) {
        Map<String, String> data = FUtils.object2Map(params);
        final StringBuilder sb = new StringBuilder();
        sb.append(hostUrl);
        if (data != null && data.size() > 0) {
            sb.append("?");
            sb.append(map2Url(data));
        } else {
            return sb;
        }

        final int length = sb.length() - 1;
        if (length >= 0 && sb.charAt(length) == '&') { // 末尾一个&符号去掉
            sb.deleteCharAt(length);
        }

        return sb;
    }

    /**
     * @param params
     * @return
     */
    public static StringBuilder params2Url(IParams params) {
        Map<String, String> data = FUtils.object2Map(params);
        final StringBuilder sb = new StringBuilder();
        if (data != null && data.size() > 0) {
            sb.append("?");
            sb.append(map2Url(data));
        } else {
            return sb;
        }
        final int length = sb.length() - 1;
        if (length >= 0 && sb.charAt(length) == '&') { // 末尾一个&符号去掉
            sb.deleteCharAt(length);
        }
        return sb;
    }

    /**
     * 对象转换成Map集合
     *
     * @param obj
     * @return
     */
    public static Map<String, String> object2Map(Object obj) {
        String json = object2JSON(obj);
        if (json == null) {
            return null;
        }

        return json2Map(json);
    }

    /**
     * 对象转换成JSON字符串
     *
     * @param obj
     * @return
     */
    public static String object2JSON(Object obj) {
        String result = null;
        Gson gson = newIgnoreGson();
        if (obj != null) {
            result = gson.toJson(obj);
        }
        if (result.contains("EXTRA_DATA")) {
            try {
                JSONObject object = new JSONObject(result);
                if (object.has("EXTRA_DATA")) {
                    JSONObject object1 = object.getJSONObject("EXTRA_DATA");
                    result = object1.toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Log.e("list转json",result);

        return result;
    }

    public static Gson newIgnoreGson() {
        IgnoreExclusionStrategy strategy = new IgnoreExclusionStrategy();
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(strategy).addDeserializationExclusionStrategy(strategy).create();
        return gson;
    }

    /**
     * 将键值对转换成URL参数
     *
     * @param data
     * @return
     */
    public final static String map2Url(Map<String, String> data) {
        Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
        StringBuilder sb = new StringBuilder(23);
        Entry<String, String> entry = null;
        Object obj = null;
        while (it.hasNext()) {
            entry = it.next();
            String key = entry.getKey();
            if (TextUtils.isEmpty(key)) {
                continue;
            }

            obj = entry.getValue();
            sb.append(key).append("=").append(obj == null ? "" : obj.toString()).append("&");
        }

        final int len = sb.length();
        if (len > 0) { // 删除末尾的&符号
            sb.deleteCharAt(len - 1);
        }

        Log.d(TAG, "map to params: " + sb.toString());
        return sb.toString();
    }

    /**
     * 将json字符串转换成Map集合
     *
     * @param json
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> json2Map(String json) {
        try {
            JSONObject jobj = new JSONObject(json); // 再转换成JSONObject
            Log.d(TAG, "params to json: " + jobj);

            // 再将JSONObject转换成Map集合
            Iterator<String> it = jobj.keys();
            String key = null;
            Map<String, String> data = new HashMap<String, String>(jobj.length());
            while (it.hasNext()) {
                key = it.next();
                data.put(key, jobj.get(key).toString());
            }

            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
