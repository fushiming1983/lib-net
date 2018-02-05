package com.android.volley.task;

import android.content.Context;

import com.android.volley.VolleyError;
import com.android.volley.pojos.result.IResult;

/**
 * @author 13120678
 *
 * 实体化网络返回接口
 */
public class AsyncTaskCallbackProxy implements ICallBackData{

	private ICallBackData mCallback;

	public AsyncTaskCallbackProxy(ICallBackData callback) {
		mCallback = callback;
	}

	@Override
	public void resolveResultData(IResult result) {
		if(mCallback != null) {
			mCallback.resolveResultData(result);
		}
	}

	@Override
	public Context getContext() {
		if(mCallback != null) {
			return mCallback.getContext();
		}
		return null;
	}

	@Override
	public void onRequestError(VolleyError error) {
		if(mCallback != null) {
			mCallback.onRequestError(error);
		}
	}


}
