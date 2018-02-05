package com.suning.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.suning.utils.AndroidLifecycleUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * Author : 13120678
 * Date   : 17/3/31 下午5:29
 * Desc   :
 */
public abstract class BaseSimpleAdapter<T> extends BaseAdapter{

    /**
     * 上下文
     */
    protected Context mContext;
    /**
     * 数据源
     */
    protected List<T> mDatas;
    /**
     * 视图导入
     */
    protected LayoutInflater mInflater;
    /**
     * 图片加载器
     */
    protected RequestManager mRequestManager;

    public BaseSimpleAdapter(Context context, List<T> data) {
        mContext = context;
        mDatas = data == null ? new ArrayList<T>() : data;
        mInflater = LayoutInflater.from(context);
    }

    public BaseSimpleAdapter(Context context, List<T> data, RequestManager requestManager) {
        this(context,data);
        mRequestManager = requestManager;
    }


    @Override
    public int getCount() {
        return mDatas.size();
    }


    @Override
    public Object getItem(int position) {
        if (position >= mDatas.size())
            return null;
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 该方法需要子类实现，需要返回item布局的resource id
     *
     * @return
     */
    public abstract int getItemResource();

    /**
     * 使用该getItemView方法替换原来的getView方法，需要子类实现
     *
     * @param position
     * @param convertView
     * @param holder
     * @return
     */
    public abstract View getItemView(int position, View convertView, ViewHolder holder);

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = mInflater.inflate(getItemResource(), null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return getItemView(position, convertView, holder);
    }

    public class ViewHolder {
        private SparseArray<View> views = new SparseArray<View>();
        private View convertView;

        public ViewHolder(View convertView) {
            this.convertView = convertView;
        }

        @SuppressWarnings("unchecked")
        public <T extends View> T getView(int resId) {
            View v = views.get(resId);
            if (null == v) {
                v = convertView.findViewById(resId);
                views.put(resId, v);
            }
            return (T) v;
        }
    }

    /**
     * 添加数据
     * @param elem
     */
    public void addAll(List<T> elem) {
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    /**
     * 移除数据
     * @param elem
     */
    public void remove(T elem) {
        mDatas.remove(elem);
        notifyDataSetChanged();
    }

    /**
     * 移除某个位置
     * @param index
     */
    public void remove(int index) {
        mDatas.remove(index);
        notifyDataSetChanged();
    }

    /**
     * 替换整个数据
     * @param elem
     */
    public void replaceAll(List<T> elem) {
        mDatas.clear();
        mDatas.addAll(elem);
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    /**
     * 加载图片
     * @param imageView
     * @param url
     */
    protected void displayImage(ImageView imageView, String url) {
        boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(mContext);

        if(canLoadImage && mRequestManager != null) {
            mRequestManager
                    .load(url)
                    .dontAnimate()
                    .into(imageView);
        }
    }


}
