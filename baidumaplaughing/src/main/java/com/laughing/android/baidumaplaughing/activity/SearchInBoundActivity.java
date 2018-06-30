package com.laughing.android.baidumaplaughing.activity;

import android.support.annotation.NonNull;

import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;

/**
 * 在范围内搜索
 * 作者：Laughing on 2016/7/29 09:58
 * 邮箱：719240226@qq.com
 */

public class SearchInBoundActivity extends PoiSearchBaseActivity {


    @Override
    public void initPoiSearch() {
        poiSearch.searchInBound(getPoiBoundSearchOption());

    }

    /**
     * 获取一个边界范围搜索选项参数方法
     *
     * @return options
     */
    @NonNull
    private PoiBoundSearchOption getPoiBoundSearchOption() {
        PoiBoundSearchOption options = new PoiBoundSearchOption();
        // 指定搜索范围，由一个西南点和一个东北点组成的范围
        LatLngBounds bounds = new LatLngBounds.Builder()
//                .include(new LatLng(40.048459, 116.302072))
//                .include(new LatLng(40.050675, 116.304317))
                .include(tengFeiPos)
                .include(beidajiePos)
                .build();
        options.bound(bounds);  // 指定搜索范围
        options.keyword("加油站");    // 指定搜索什么内容
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
