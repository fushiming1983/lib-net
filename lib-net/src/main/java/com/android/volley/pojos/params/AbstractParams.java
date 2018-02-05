package com.android.volley.pojos.params;


/**
 * @author 13120678
 */
public abstract class AbstractParams implements IParams {

    private transient Object mTag;
    private transient Object mTag2;

    private transient Object mExtObject;  // 扩展存储对象
    private transient boolean mExtFlag;   // 扩展标识1
    private transient boolean mExtFlag2;  // 扩展标识2

    /**
     * 超时
     */
    private transient int TIME_OUT     = 6 * 1000;
    /**
     * 重试的次数
     */
    private transient  int RETRY_COUNT  = 1;

    @Override
    public Object getTag() {
        
        return mTag;
    }

    @Override
    public void setTag(Object tag) {
        
        mTag = tag;
    }

    @Override
    public Object getTag2() {
        
        return mTag2;
    }

    @Override
    public void setTag2(Object tag) {
        
        mTag2 = tag;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public boolean isJson() {
        return false;
    }

    @Override
    public boolean isCache() {
        return false;
    }


    @Override
    public int getTimeOut() {
        return TIME_OUT;
    }

    @Override
    public void setTimeOut(int time) {
        TIME_OUT=time;
    }

    @Override
    public int getRetryCount() {
        return RETRY_COUNT;
    }

    @Override
    public void setRetryCount(int times) {
        RETRY_COUNT=times;
    }

    @Override
    public void setExtObject(Object extObject) {
        mExtObject = extObject;
    }

    @Override
    public Object getExtObject() {
        return mExtObject;
    }

    @Override
    public void setExtFlag(boolean flag) {
        mExtFlag = flag;
    }

    @Override
    public boolean getExtFlag() {
        return mExtFlag;
    }

    @Override
    public void setExtFlag2(boolean flag) {
        mExtFlag2 = flag;
    }

    @Override
    public boolean getExtFlag2() {
        return mExtFlag2;
    }
}
