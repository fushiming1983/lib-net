package com.suning.base;

import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * Author : 13120678
 * Date   : 17/3/30 下午9:29
 * Desc   : 刷新的基类
 */
public abstract class BaseRvActivity extends BaseActivity{

    /**
     * Rv控件
     */
    protected RecyclerView rv;




    @Override
    public void handleMessage(BaseActivity aty, Message msg) {

    }


    @Override
    protected void initExtra() {
        if(rv != null) {
            configRv();
        }
    }

    /**
     * 配置Rv
     */
    protected void configRv() {
        rv.setLayoutManager(new LinearLayoutManager(this));

    }


}
