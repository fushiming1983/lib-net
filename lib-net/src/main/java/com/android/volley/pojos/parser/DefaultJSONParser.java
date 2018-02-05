package com.android.volley.pojos.parser;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.pojos.params.IParams;
import com.android.volley.pojos.result.IResult;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author 13120678
 *         JSON格式数据的解析
 */
public class DefaultJSONParser extends IParser {

    private static final String TAG = DefaultJSONParser.class.getSimpleName();

    @Override
    public void setResult(String result, IParams params) throws VolleyError {
        if (TextUtils.isEmpty(result)) {
            VolleyLog.wtf(TAG, "this result is null.");
            return;
        }
        Log.e("返回数据",result);
        mResult = parser(result);
    }

    /**
     * 解析类
     *
     * @param result
     * @return
     */
    protected IResult parser(String result) throws VolleyError {
        IResult iResult = null;
        Gson gson = null;
        gson = new Gson();
        final Class<? extends IResult> clazz = mClazz;
        try {
            iResult = gson.fromJson(result, clazz);
        } catch (JsonSyntaxException e) {
            VolleyLog.wtf(TAG, "throw= " + e.getMessage());
            throw new VolleyError();
        }
        return iResult;
    }

}
