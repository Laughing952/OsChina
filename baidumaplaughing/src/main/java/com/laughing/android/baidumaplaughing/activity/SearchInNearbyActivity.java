package com.laughing.android.baidumaplaughing.activity;

import android.support.annotation.NonNull;

import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;

/**
 * 在周边内搜索
 * 作者：Laughing on 2016/7/29 09:58
 * 邮箱：719240226@qq.com
 */

public class SearchInNearbyActivity extends PoiSearchBaseActivity {


    @Override
    public void initPoiSearch() {
        poiSearch.searchNearby(getPoiBoundSearchOption());

    }

    /**
     * 获取一个边界范围搜索选项参数方法
     *
     * @return options
     */
    @NonNull
    private PoiNearbySearchOption getPoiBoundSearchOption() {
        PoiNearbySearchOption options = new PoiNearbySearchOption();
        options.location(tengFeiPos);   // 指定在哪个位置搜索
        options.radius(1000);  // 指定搜索范围
        options.keyword("工商银行");    // 指定搜索什么内容
        return options;
    }

    /**
     * 获取检索信息详情
     *
     * @param poiDetailResult
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    /**
     * @param poiIndoorResult
     */
    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }
}
