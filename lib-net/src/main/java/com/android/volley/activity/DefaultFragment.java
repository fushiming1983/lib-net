/*
 * Copyright (C), 2002-2016, 苏宁易购电子商务有限公司
 * FileName: DefaultFragment.java
 * Author:   14074533
 * Date:     2016-2-22 下午2:39:33
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.android.volley.activity;

import android.content.Context;

import com.android.volley.VolleyError;
import com.android.volley.pojos.result.IResult;
import com.android.volley.task.ICallBackData;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * @author 14074533
 */
public abstract class DefaultFragment extends SupportFragment implements ICallBackData {

    @Override
    public void resolveResultData(IResult result) {

    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void onRequestError(VolleyError error) {

    }

}
