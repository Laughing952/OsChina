package com.laughing.android.baidumaplaughing.activity;

import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.laughing.android.baidumaplaughing.overlayutil.TransitRouteOverlay;

import java.util.List;

/**公交，地铁换乘线路
 * 作者：Laughing on 2016/7/29 17:52
 * 邮箱：719240226@qq.com
 */

public class TransitSearchActivity extends RoutePlanSearchBaseActivity {

    @Override
    public void routePlanSearchInit() {
        mRoutePlanSearch.transitSearch(getSearchParams());
    }

    private TransitRoutePlanOption getSearchParams() {
        TransitRoutePlanOption option = new TransitRoutePlanOption();
        option.city("西安");//不能少此项
        option.policy(TransitRoutePlanOption.TransitPolicy.EBUS_TIME_FIRST);//模式：时间优先（还有其他模式）
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

    }

    /**
     * 获取换乘（公交、地铁）搜索结果的回调方法
     *
     * @param transitRouteResult
     */
    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
        TransitRouteOverlay overlay = new TransitRouteOverlay(baiduMap);
        baiduMap.setOnMarkerClickListener(overlay);
        List<TransitRouteLine> routeLines = transitRouteResult.getRouteLines();
        if (routeLines != null) {
            //获取到所有的搜索路线，最优化的路线会在集合的前面
            overlay.setData(routeLines.get(0));// 把搜索结果设置到覆盖物
            overlay.addToMap();// 把搜索结果添加到地图
            overlay.zoomToSpan();// 把搜索结果在一个屏幕内显示完
        } else {
            showToast("请查看您网络是否给力");
        }
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
