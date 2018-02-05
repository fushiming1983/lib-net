package com.android.volley.pojos.params;

import com.android.volley.Request.Method;
import com.android.volley.pojos.parser.IParser;
import com.android.volley.pojos.result.IResult;


/**
 * @author 13120678
 *         <p/>
 *         请求参数的接口类
 */
public interface IParams {

    /**
     * post提交方式标识
     */
    public static final int DO_POST = Method.POST;

    /**
     * get提交方式
     */
    public static final int DO_GET = Method.GET;

    /**
     * 返回获取数据或提交数据的方式
     *
     * @return
     */
    public int getDoType();

    /**
     * 返回请求地址，不包括主机地址
     */
    public String getAction();

    /**
     * 返回结果数据解析器
     *
     * @return
     */
    public IParser getParser();

    /**
     * 返回结果类的类型
     *
     * @return
     */
    public Class<? extends IResult> getResultClass();

    /**
     * 返回请求标识
     *
     * @return
     */
    public Object getTag();

    /***
     * 设置请求标识
     *
     * @param tag
     */
    public void setTag(Object tag);


    /**
     * 返回请求标识
     *
     * @return
     */
    public Object getTag2();

    /***
     * 设置请求标识
     *
     * @param tag
     */
    public void setTag2(Object tag);

    /**
     * 请求的主机地址
     */
    public String getHost();

    /**
     * 入参是否是Json类型
     *
     * @return
     */
    public boolean isJson();

    /**
     * 是否缓存数据
     * @return
     */
    public boolean isCache();

    /**
     * 获取超时的配置
     * @return
     */
    public int getTimeOut();

    /**
     * 设置超时的配置
     */
    public void setTimeOut(int time);

    /**
     * 获取请求重试的次数
     * @return
     */
    public int getRetryCount();

    /**
     * 设置请求重试的次数
     * @return
     */
    public void setRetryCount(int times);

    /**
     * 设置扩展存储对象
     * @param extObject
     */
    void setExtObject(Object extObject);

    /**
     * 获取存储的扩展对象
     * @return
     */
    Object getExtObject();

    /**
     * 设置扩展标识
     * @param flag
     */
    void setExtFlag(boolean flag);

    /**
     * 或者扩展标识
     * @return
     */
    boolean getExtFlag();

    /**
     * 设置扩展标识
     * @param flag
     */
    void setExtFlag2(boolean flag);

    /**
     * 或者扩展标识
     * @return
     */
    boolean getExtFlag2();
}
