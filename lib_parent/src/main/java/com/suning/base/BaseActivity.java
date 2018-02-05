package com.suning.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.android.volley.activity.DefaultActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
//import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;

/**
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * Author : 13120678
 * Date   : 17/3/30 下午9:32
 * Desc   : 基类
 */
public abstract class BaseActivity extends DefaultActivity{

    protected BaseHandler mHandler = new BaseHandler(this);

    /**
     * 基类统一配置使用
     */
    protected RequestManager mRequestManager;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(bindLayout());

        mRequestManager = Glide.with(this);

        initPre(arg0);
        initExtra();
    }


    /**
     * 捆绑视图
     * @return
     */
    protected abstract int bindLayout();


    /**
     * 根据bundle处理
     * @param bundle
     */
    protected void initPre(Bundle bundle) {

    }

    /**
     * 初始化操作
     */
    protected abstract void initExtra();


    /**
     * 消息处理
     *
     * @param aty
     * @param msg
     */
    public abstract void handleMessage(BaseActivity aty, Message msg);


    /**
     * Handler类
     */
    public static class BaseHandler extends Handler {

        private WeakReference<BaseActivity> ref;

        public BaseHandler(BaseActivity activity) {
            ref = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final BaseActivity ac = ref.get();
            if (ac == null || ac.isFinishing()) {
                return;
            }
            ac.handleMessage(ac, msg);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

}


