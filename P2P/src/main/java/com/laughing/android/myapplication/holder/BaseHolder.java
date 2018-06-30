package com.laughing.android.myapplication.holder;

import android.view.View;

/**
 * 作者：Laughing on 2016/8/12 09:30
 * 邮箱：719240226@qq.com
 */

public abstract class BaseHolder<T> {

    public BaseHolder() {
        getRootView();
    }

    public abstract View getRootView();

    public abstract <T> void setData(T t);

    public abstract void refreshData();

}
