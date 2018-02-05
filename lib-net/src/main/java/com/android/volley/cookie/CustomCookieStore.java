package com.android.volley.cookie;


import java.util.List;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by 14074533 on 16/10/11.
 */

public interface CustomCookieStore {

    /**
     * 保存url对应所有cookie
     */
    void saveCookies(HttpUrl url, List<Cookie> cookie);

    /**
     * 加载url所有的cookie
     */
    List<Cookie> loadCookies(HttpUrl url);

    /**
     * 获取当前所有保存的cookie
     */
    List<Cookie> getAllCookie();

    /**
     * 根据url和cookie移除对应的cookie
     */
    boolean removeCookie(HttpUrl url, Cookie cookie);

    /**
     * 根据url移除所有的cookie
     */
    boolean removeCookies(HttpUrl url);

    /**
     * 移除所有的cookie
     */
    boolean removeAllCookie();


    /**
     * 获取所有未过期的cookie
     *
     * @return
     */
    List<Cookie> getAllCookieNotExpired(HttpUrl url);
}
