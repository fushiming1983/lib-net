package com.android.volley.activity;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

/**
 * Created by 14074533 on 2016/11/1.
 */

public class DefaultApplication extends Application {

    private static Bus mBus;

    @Override
    public void onCreate() {
        super.onCreate();
        mBus = new Bus();
    }

    public static Bus getBus() {
        return mBus;
    }

    public static void postEvent(final Object obj) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            mBus.post(obj);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() {

                @Override
                public void run() {
                    if (mBus==null){
                        return;
                    }
                    mBus.post(obj);
                }
            });
        }
    }
}
