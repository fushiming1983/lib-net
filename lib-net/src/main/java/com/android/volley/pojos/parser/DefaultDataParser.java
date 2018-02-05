package com.android.volley.pojos.parser;

import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.pojos.params.IParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 14074533 on 2016/9/1.
 */
public class DefaultDataParser extends DefaultJSONParser {
    private static final String TAG = DefaultDataParser.class.getSimpleName();

    @Override
    public void setResult(String result, IParams params) {
        if (TextUtils.isEmpty(result)) {
            VolleyLog.wtf(TAG, "this result is null.");
            return;
        }

        try {
            // 手动添加key：data
            JSONObject responseObj = new JSONObject(result);
            JSONObject object = new JSONObject();
            object.put("data", responseObj);
            String json = object.toString();

            mResult = parser(json);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (VolleyError volleyError) {
            volleyError.printStackTrace();
        }

    }
}
