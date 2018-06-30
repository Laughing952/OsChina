package com.laughing.android.baidumaplaughing.activity;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.laughing.android.baidumaplaughing.overlayutil.PoiOverlay;
import com.laughing.android.baidumaplaughing.util.ToastUtils;

/**
 * 搜索统一的父类
 * 作者：Laughing on 2016/7/29 12:51
 * 邮箱：719240226@qq.com
 */

public abstract class PoiSearchBaseActivity extends BaseMapActivity implements OnGetPoiSearchResultListener {

    protected PoiSearch poiSearch;
    protected PoiOverlay poiOverlay;

    @Override
    public void init() {
        // 因为其它搜索也需要这个实例，所以放在父类初始，这样的话,子类就不需要再实例化
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(this);
        poiOverlay = new PoiOverlay(baiduMap) {
            @Override
            public boolean onPoiClick(int i) {
                return PoiSearchBaseActivity.this.onPoiClick(i);//自己处理点击事件return true
            }
        };
        baiduMap.setOnMarkerClickListener(poiOverlay);//点击覆盖物监听方法

        initPoiSearch();
    }

    /**
     * 生成这个方法，是为了让子类可以覆盖(当子类需要查询详情时复写此方法)
     *
     * @param i
     * @return
     */
    public boolean onPoiClick(int i) {
        PoiInfo poiInfo = poiOverlay.getPoiResult().getAllPoi().get(i);
        ToastUtils.showToastPlus(getApplicationContext(), poiInfo.name + " " + poiInfo.address);

        return true;//自己处理点击事件return true
    }


    public abstract void initPoiSearch();

    /**
     * 复写setOnGetPoiSearchResultListener方法
     * 获取检索信息
     *
     * @param poiResult
     */
    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult == null) {
            return;
        }
        if (poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            ToastUtils.showToastPlus(getApplicationContext(), "没有找到搜索结果");
            return;
        } else if (poiResult.error == SearchResult.ERRORNO.KEY_ERROR) {
            ToastUtils.showToastPlus(getApplicationContext(), "key错误");
            return;
        } else if (poiResult.error == SearchResult.ERRORNO.NETWORK_TIME_OUT) {
            ToastUtils.showToastPlus(getApplicationContext(), "网络超时");
            return;
        } else if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {

            poiOverlay.setData(poiResult);    // 把数据设置给覆盖物
            poiOverlay.addToMap();      // 把所有的数据的变成覆盖添加到BaiduMap
            poiOverlay.zoomToSpan();    // 把所有的搜索结果在一个屏幕内显示出来
        }
    }


}
