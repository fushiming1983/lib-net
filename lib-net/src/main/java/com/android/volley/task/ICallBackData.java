package com.android.volley.task;

import android.content.Context;
import android.os.Handler;

import com.android.volley.VolleyError;
import com.android.volley.pojos.result.IResult;

/**
 * @author 13120678
 * 异步回调接口
 */
public interface ICallBackData {

    /**
     * 处理网络请求后返回的结果
     *
     * @param result
     */
    public void resolveResultData(IResult result);
    /**
     * 获取Context对象
     * @return
     */
    public Context getContext();
    /**
     * 处理网络请求的异常
     */
    public void onRequestError(VolleyError error);

}
