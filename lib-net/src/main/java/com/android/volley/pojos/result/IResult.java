package com.android.volley.pojos.result;

/**
 * @author 13120678
 *         网络请求返回结果数据保存对象
 */
public class IResult {

    /**
     * 解析出错标识
     */
    public static final int FLAG_PARSER_ERROR = Integer.MIN_VALUE;

    /**
     * 返回的ID
     */
    public transient int Result = Integer.MIN_VALUE;

    /**
     * 返回的消息
     */
    public transient String Message;

    /**
     * 标记对象
     */
    private Object mTag;

    private Object mTag2;

    public final Object getTag() {
        return mTag;
    }

    public final void setTag(Object tag) {
        mTag = tag;
    }

    public final Object getTag2() {
        return mTag2;
    }

    public final void setTag2(Object tag) {
        mTag2 = tag;
    }

    @Override
    public String toString() {
        return "result:" + Result + ", msg: " + Message + ", tag: " + mTag;
    }
}
