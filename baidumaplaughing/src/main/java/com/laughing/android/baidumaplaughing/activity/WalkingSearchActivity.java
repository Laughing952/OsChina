package com.laughing.android.baidumaplaughing.activity;

import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.laughing.android.baidumaplaughing.overlayutil.WalkingRouteOverlay;

import java.util.List;

/**
 * 步行线路
 * 作者：Laughing on 2016/7/29 17:52
 * 邮箱：719240226@qq.com
 */

public class WalkingSearchActivity extends RoutePlanSearchBaseActivity {

    @Override
    public void routePlanSearchInit() {
        mRoutePlanSearch.walkingSearch(getSearchParams());
    }

    private WalkingRoutePlanOption getSearchParams() {
        WalkingRoutePlanOption option = new WalkingRoutePlanOption();
        option.from(PlanNode.withLocation(tengFeiPos));
        option.to(PlanNode.withLocation(beidajiePos));
        return option;
    }

    /**
     * 获取步行搜索结果的回调方法
     *
     * @param walkingRouteResult
     */
    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
        WalkingRouteOverlay overlay = new WalkingRouteOverlay(baiduMap);
        baiduMap.setOnMarkerClickListener(overlay);
        List<WalkingRouteLine> routeLines = walkingRouteResult.getRouteLines();
        if (routeLines != null) {
            //获取到所有的搜索路线，最优化的路线会在集合的前面
            WalkingRouteLine walkingRouteLine = routeLines.get(0);
            overlay.setData(walkingRouteLine);
            overlay.addToMap();
            overlay.zoomToSpan();
        } else {
            showToast("请查看您网络是否给力");
        }


    }

    /**
     * 获取换乘（公交、地铁）搜索结果的回调方法
     *
     * @param transitRouteResult
     */
    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    /**
     * 跨城公共路线检索
     *
     * @param massTransitRouteResult
     */
    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    /**
     * 获取驾车搜索结果的回调方法
     *
     * @param drivingRouteResult
     */
    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {


    }

    /**
     * 室内路线规划
     *
     * @param indoorRouteResult
     */
    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    /**
     * 骑行路线规划
     *
     * @param bikingRouteResult
     */
    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }
}
