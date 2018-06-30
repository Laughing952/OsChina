package com.laughing.android.myapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.laughing.android.myapplication.holder.BaseHolder;

import java.util.List;

/**此adapter封装的比较易于理解因为都是按照listView原来baseAdapter来行执行的顺序
 * 作者：Laughing on 2016/8/11 17:42
 * 邮箱：719240226@qq.com
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    protected List<T> list;

    public MyBaseAdapter(List<T> list) {
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
        BaseHolder holder = null;
        if (convertView == null) {
            holder = getHolder();//创建holder
            convertView = holder.getRootView();//添加item布局
            convertView.setTag(holder);//设置tag
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        holder.setData(list.get(position));//获取item数据，找到空间
        holder.refreshData();//填充数据
        return convertView;
    }

    protected abstract BaseHolder getHolder();//子类创建一个holder对象


}
