package com.android.volley.request;

import com.android.volley.pojos.result.IResult;

/**
 * @author 13120678
 * 结果基类
 */
public class BaseResult extends IResult{

    // 成功的标示
    public static final String SUCCESS = "0";

    /**
     * 返回码，0-成功
     */
    public String retCode;

    /**
     * 返回信息
     */
    public String retMsg;

}
