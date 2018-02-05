package com.android.volley.pojos.parser;

import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.pojos.params.IParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gongchaobin on 16/8/20.
 */
public class DefaultArrayParser extends DefaultJSONParser{

    private static final String TAG = DefaultArrayParser.class.getSimpleName();

    @Override
    public void setResult(String result, IParams params) throws VolleyError{
        if(TextUtils.isEmpty(result)) {
            VolleyLog.wtf(TAG, "this result is null.");
            return;
        }

        try {
            // 手动将JSONArray拼凑成JSONObject对象
            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data",jsonArray);
            String json = jsonObject.toString();

            mResult = parser(json);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
