package com.laughing.android.baidumaplaughing.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.laughing.android.baidumaplaughing.R;
import com.laughing.android.baidumaplaughing.util.ToastUtils;

/**
 * 在城市范围内搜索
 * 作者：Laughing on 2017/7/29 14:39
 * 邮箱：719240226@qq.com
 */

public class SearchInCityActivity extends PoiSearchBaseActivity {
    private int pageNum = 0;

    @Override
    public void initPoiSearch() {
        LinearLayout ll_next = (LinearLayout) findViewById(R.id.ll_next);
        ll_next.setVisibility(View.VISIBLE);
        poiSearch.searchInCity(getSearchParams());

    }

    @Override
    public boolean onPoiClick(int index) {
        // 当点击一个搜索出来的兴趣点的时候，再去搜索更详细的内容
        PoiInfo poiInfo = poiOverlay.getPoiResult().getAllPoi().get(index);
        poiSearch.searchPoiDetail(getSearchDetailParams(poiInfo.uid));
        return true;
    }

    private PoiDetailSearchOption getSearchDetailParams(String poiUid) {
        PoiDetailSearchOption option = new PoiDetailSearchOption();
        option.poiUid(poiUid);
        return option;
    }

    /**
     * 获取一个城市范围搜索选项参数方法
     *
     * @return options
     */
    private PoiCitySearchOption getSearchParams() {
        PoiCitySearchOption options = new PoiCitySearchOption();
        options.city("西安"); // 指定在哪个城市内进行搜索
        options.keyword("加油站"); // 指定搜索的是什么内容
        options.pageCapacity(10);   // 设置一页获取10条数据
        options.pageNum(pageNum);   // 指定获取哪一页
        return options;
    }


    /**
     * 复写父类的获取到详情信息的回调方法
     *
     * @param poiDetailResult
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        if (poiDetailResult == null) {
            return;
        }
        if (poiDetailResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            ToastUtils.showToastPlus(getApplicationContext(), "没有找到搜索结果");
            return;
        } else if (poiDetailResult.error == SearchResult.ERRORNO.KEY_ERROR) {
            ToastUtils.showToastPlus(getApplicationContext(), "key错误");
            return;
        } else if (poiDetailResult.error == SearchResult.ERRORNO.NETWORK_TIME_OUT) {
            ToastUtils.showToastPlus(getApplicationContext(), "网络超时");
            return;
        } else if (poiDetailResult.error == SearchResult.ERRORNO.NO_ERROR) {

            ToastUtils.showToastPlus(getApplicationContext(), poiDetailResult.getShopHours() + " " + poiDetailResult.getTelephone());
        }


//        if (poiDetailResult == null || poiDetailResult.error != SearchResult.ERRORNO.NO_ERROR) {
//            showToast("没有搜索到详情信息");
//            return;
//        }
//
//        showToast(poiDetailResult.getShopHours() + ", " + poiDetailResult.getTelephone());
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }

    public void click(View v) {
        switch (v.getId()) {
            case R.id.bt_last:
                pageNum--;
                if (pageNum <= 0) {
                    pageNum = 0;
                }
                poiSearch.searchInCity(getSearchParams());
                break;
            case R.id.bt_next:
                pageNum++;
                poiSearch.searchInCity(getSearchParams());
                break;

            default:

                break;
        }
    }
}
