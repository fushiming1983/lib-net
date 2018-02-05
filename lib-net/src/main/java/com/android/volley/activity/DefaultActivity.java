package com.android.volley.activity;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.VolleyError;
import com.android.volley.pojos.result.IResult;
import com.android.volley.task.AsyncTaskCallbackProxy;
import com.android.volley.task.ICallBackData;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * @author 13120678
 *         界面的基类，主要是网络异常及网络数据返回的处理
 */
public abstract class DefaultActivity extends SupportActivity implements ICallBackData {

    /**
     * 请求结果返回回调处理和网络异常回调处理
     */
    private AsyncTaskCallbackProxy mProxy;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        if (mProxy == null) {
            mProxy = new AsyncTaskCallbackProxy(this);
        }
    }

    @Override
    public void resolveResultData(IResult result) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onRequestError(VolleyError error) {

    }

}
