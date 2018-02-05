package com.suning.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

/**
 * @author 13120678
 *         <p>
 *         轻量级的SharedPreferences数据缓存类
 */
public class PreferencesHelper {
    private Context context = null;// 上下文
    private String name = "";// 名称
    private SharedPreferences preferences = null;

    public PreferencesHelper(Context ctx, String name) {
        context = ctx;
        this.name = name;
        preferences = getPreferences();
    }

    private SharedPreferences getPreferences() {
        SharedPreferences per = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return per;
    }

    public boolean setString(String key, String val) {
        return preferences.edit().putString(key, val).commit();
    }

    public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public boolean setBoolean(String key, boolean val) {
        return preferences.edit().putBoolean(key, val).commit();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public boolean setFloat(String key, float val) {
        return preferences.edit().putFloat(key, val).commit();
    }

    public float getFloat(String key, float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    public boolean setInt(String key, int val) {
        return preferences.edit().putInt(key, val).commit();
    }

    public int getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public boolean setLong(String key, long val) {
        return preferences.edit().putLong(key, val).commit();
    }

    public long getLong(String key, long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    public boolean setObject(String key, Object object) {
        return setString(key, new Gson().toJson(object));
    }

    public Object getObject(String key, Class clazz) {
        return new Gson().fromJson(getString(key, ""), clazz);
    }

    public Map<String, ?> getAll() {
        return preferences.getAll();
    }

    public void clear() {
        Editor editor = preferences.edit();
        Map<String, ?> map = preferences.getAll();
        for (Map.Entry<String, ?> e : map.entrySet()) {
            editor.remove(e.getKey());
            editor.commit();
        }
    }


    /**
     * 序列化对象
     *
     * @param
     * @return
     * @throws IOException
     */
    private String serialize(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            String serStr = byteArrayOutputStream.toString("ISO-8859-1");
            serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
            objectOutputStream.close();
            byteArrayOutputStream.close();
            return serStr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 反序列化对象
     *
     * @param str
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Object deSerialization(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String redStr = java.net.URLDecoder.decode(str, "UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(redStr.getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = (Object) objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
