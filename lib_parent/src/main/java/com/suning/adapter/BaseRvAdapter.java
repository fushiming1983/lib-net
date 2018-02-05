package com.suning.adapter;

import android.content.Context;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.Collection;
import java.util.List;

/**
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * Author : 13120678
 * Date   : 17/3/30 下午9:41
 * Desc   : RecyleViewAdapter的基类
 */
public abstract class BaseRvAdapter<T> extends MultiItemTypeAdapter<T>{

    public BaseRvAdapter(Context context, List<T> datas) {
        super(context, datas);
    }


    /**
     * 设置数据
     * @param datas
     */
    public void setDataList(Collection<T> datas) {
        mDatas.clear();
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     * 添加数据
     * @param list
     */
    public void addAll(Collection<T> list) {
        int lastIndex = this.mDatas.size();
        if (this.mDatas.addAll(list)) {
            notifyItemRangeInserted(lastIndex, list.size());
        }
    }

    /**
     * 移除某个位置的数据
     * @param position
     */
    public void remove(int position) {
        if (this.mDatas.size() > 0) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * 移除某个对象
     * @param object
     */
    public void remove(T object) {
        if (this.mDatas.size() > 0) {
            mDatas.remove(object);
            notifyDataSetChanged();
        }
    }

    /**
     * 清空数据
     */
    public void clear() {
        mDatas.clear();
        notifyDataSetChanged();
    }


}
