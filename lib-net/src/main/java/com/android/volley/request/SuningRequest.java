package com.android.volley.request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.cookie.CookieJarManager;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by 14074533 on 16/10/12.
 */

public class SuningRequest extends StringRequest {

    public SuningRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public SuningRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        // 添加Cookie
        if (userCookies.size() > 0) {
            CookieJarManager.getCookieJar().addCookies(userCookies);
        }
        return super.getHeaders();
    }

    /*************************************
     * 添加cookie by14074533
     ************************************/

    /**
     * 用户手动添加的Cookie
     */
    private List<Cookie> userCookies = new ArrayList<Cookie>();

    /**
     * 添加Cookie
     *
     * @param name
     * @param value
     */
    public void addCookie(String name, String value) {
        Cookie.Builder builder = new Cookie.Builder();
        Cookie cookie = null;
        try {
            cookie = builder.name(name).value(value).domain(HttpUrl.parse(getUrl()).host()).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cookie != null) {
            userCookies.add(cookie);
        }
    }

    /**
     * 添加Cookie
     *
     * @param name
     * @param value
     */
    public void addCookieByDomain(String name, String value) {
        Cookie.Builder builder = new Cookie.Builder();
        Cookie cookie = null;
        try {
            String domain = HttpUrl.parse(getUrl()).host();
            cookie = builder.name(name).value(value).domain(domain).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (cookie != null) {
            userCookies.add(cookie);
        }
    }

    /**
     * 添加Cookie
     *
     * @param cookie
     */
    public void addCookie(Cookie cookie) {
        userCookies.add(cookie);
    }

    /**
     * 添加Cookies
     *
     * @param cookies
     */
    public void addCookies(List<Cookie> cookies) {
        userCookies.addAll(cookies);
    }

}
