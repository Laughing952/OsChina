package com.laughing.android.myapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 作者：Laughing on 2016/8/11 17:42
 * 邮箱：719240226@qq.com
 */

public abstract class MySimpleBaseAdapter<T> extends BaseAdapter {
    protected List<T> list;

    public MySimpleBaseAdapter(List<T> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getYourView(position,convertView,parent);
    }


    protected abstract View getYourView(int position, View convertView, ViewGroup parent);
}
