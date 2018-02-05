package com.android.volley.cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * Created by 14074533 on 16/10/11.
 */

public class CookieJarImpl implements CookieJar {
    private CustomCookieStore cookieStore;
    private Map<String, Set<Cookie>> userCookies = new HashMap<String, Set<Cookie>>();  //用户手动添加的Cookie

    public void addCookies(List<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            String domain = cookie.domain();
            Set<Cookie> domainCookies = userCookies.get(domain);
            if (domainCookies == null) {
                domainCookies = new HashSet<Cookie>();
                userCookies.put(domain, domainCookies);
            }
            domainCookies.add(cookie);
        }
    }

    public CookieJarImpl(CustomCookieStore cookieStore) {
        if (cookieStore == null) {
            throw new IllegalArgumentException("cookieStore can not be null!");
        }
        this.cookieStore = cookieStore;
    }

    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        cookieStore.saveCookies(url, cookies);
    }

    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url) {
        // 暂时解决登录问题。带上全部cookie，不根据url携带cookie。
        //List<Cookie> requestUrlCookies = cookieStore.loadCookies(url);
        List<Cookie> requestUrlCookies = cookieStore.getAllCookieNotExpired(url);
        Set<Cookie> userUrlCookies = userCookies.get(url.host());
        Set<Cookie> cookieSet = new HashSet<Cookie>();
        if (requestUrlCookies != null) cookieSet.addAll(requestUrlCookies);
        if (userUrlCookies != null) cookieSet.addAll(userUrlCookies);
        return new ArrayList<Cookie>(cookieSet);
    }

    public CustomCookieStore getCookieStore() {
        return cookieStore;
    }
}
