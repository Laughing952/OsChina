package com.laughing.android.baidumaplaughing.activity;

import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;

/**
 * 路径搜索基类用来初始化各种出行方式
 * 作者：Laughing on 2016/7/29 16:56
 * 邮箱：719240226@qq.com
 */

public abstract class RoutePlanSearchBaseActivity extends BaseMapActivity implements OnGetRoutePlanResultListener {

    protected RoutePlanSearch mRoutePlanSearch;

    @Override
    public void init() {
        mRoutePlanSearch = RoutePlanSearch.newInstance();
        mRoutePlanSearch.setOnGetRoutePlanResultListener(this);
        routePlanSearchInit();
    }

    /**
     * 子类路径搜索初化代码写在这个方法中
     */
    public abstract void routePlanSearchInit();
}
