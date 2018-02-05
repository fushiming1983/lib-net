package com.suning.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.suning.utils.AndroidLifecycleUtils;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * Author : 13120678
 * Date   : 17/3/30 下午9:47
 * Desc   :
 */
public abstract class BaseRvCommonAdapter<T> extends BaseRvAdapter<T>{

    /**
     * 上下文句柄
     */
    protected Context mContext;
    /**
     * 布局ID
     */
    protected int mLayoutId;
    /**
     * 布局操作类
     */
    protected LayoutInflater mInflater;
    /**
     * 加载器
     */
    protected RequestManager mRequestManager;


    public BaseRvCommonAdapter(Context context, final int layoutId, List<T> datas) {
        super(context, datas);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;

        addItemViewDelegate(new ItemViewDelegate<T>() {
            @Override
            public int getItemViewLayoutId() {
                return layoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(ViewHolder holder, T t, int position) {
                BaseRvCommonAdapter.this.convert(holder, t, position);
            }
        });
    }


    public BaseRvCommonAdapter(Context context, RequestManager requestManager, final int layoutId, List<T> datas) {
        this(context,layoutId,datas);
        mRequestManager = requestManager;
    }

    /**
     * 抽象方法 提供给子类绑定视图
     * @param holder
     * @param t
     * @param position
     */
    protected abstract void convert(ViewHolder holder, T t, int position);


    /**
     * 加载图片
     * @param imageView
     * @param url
     */
    protected void displayImage(ImageView imageView,String url) {
        boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(mContext);

        if(canLoadImage) {
            mRequestManager
                    .load(url)
                    .dontAnimate()
                    .into(imageView);
        }
    }

}
