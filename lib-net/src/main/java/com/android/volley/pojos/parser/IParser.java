package com.android.volley.pojos.parser;

import com.android.volley.VolleyError;
import com.android.volley.pojos.params.IParams;
import com.android.volley.pojos.result.IResult;

/**
 * @author 13120678
 *         网络数据解析
 */
public abstract class IParser {

    /**
     * 解析结果
     */
    protected IResult mResult;

    /**
     * 结果类的类型对象
     */
    protected Class<? extends IResult> mClazz;

    public IParser() {
    }

    public final void setResult(String result) throws VolleyError {
        setResult(result, null);
    }

    public abstract void setResult(String result, IParams params) throws VolleyError;

    /**
     * 返回解析结果数据对象
     *
     * @return
     */
    public final IResult getParseResult() {
        return mResult;
    }

    /**
     * 设置Class类
     *
     * @param clzz
     */
    public final void setResultClass(Class<? extends IResult> clzz) {
        mClazz = clzz;
    }
}
