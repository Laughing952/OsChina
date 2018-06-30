package com.laughing.android.baidumaplaughing.activity;

import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.laughing.android.baidumaplaughing.R;
import com.laughing.android.baidumaplaughing.util.DesencityUtil;

/**
 * 标志覆盖物显示标题，并且可以拖动（重点）
 * 作者：Laughing on 2016/7/27 19:36
 * 邮箱：719240226@qq.com
 */
public class MarkerOverlayActivity extends BaseMapActivity {


    private View pop;
    private TextView tv_title;

    @Override
    public void init() {
        initMarker();
        initListener();
    }

    /**
     * 初始化标志物移动监听并实现
     */
    private void initListener() {
        /**
         * 标志物点击监听（点击显示pop）
         */
        baiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // 显示一个泡泡
                if (pop == null) {
                    pop = View.inflate(MarkerOverlayActivity.this, R.layout.pop, null);
                    tv_title = (TextView) pop.findViewById(R.id.tv_title);
                    mMapView.addView(pop, createLayoutParams(marker.getPosition()));
                } else {
                    mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));
                }
                tv_title.setText(marker.getTitle());//设置初始化布局时定义的标题
                return true;//自己处理事件return true
            }
        });
        /**
         * 标志物移动监听（让pop跟随移动）
         */
        baiduMap.setOnMarkerDragListener(new BaiduMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                //标志开始拖动
                mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));

            }

            @Override
            public void onMarkerDrag(Marker marker) {
                //标志正在拖动
                mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                //标志拖动结束
                mMapView.updateViewLayout(pop, createLayoutParams(marker.getPosition()));

            }
        });
    }

    /**
     * 创建一个布局参数
     *
     * @param position
     * @return 一个布局参数
     */
    private MapViewLayoutParams createLayoutParams(LatLng position) {
        MapViewLayoutParams.Builder builder = new MapViewLayoutParams.Builder();
        builder.layoutMode(MapViewLayoutParams.ELayoutMode.mapMode);// 指定坐标类型为经纬度
        builder.position(position);// 设置标志的位置
        builder.yOffset(-(DesencityUtil.dp2pix(MarkerOverlayActivity.this, 30)));// 设置View往上偏移
        MapViewLayoutParams layoutParams = builder.build();
        return layoutParams;
    }

    /**
     * 初始化标志覆盖物
     */
    private void initMarker() {
        MarkerOptions options = new MarkerOptions();
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.icon_eat);
        options.position(beidajiePos)    // 位置
                .title("北大街")        // title
                .icon(icon)        // 图标
                .draggable(true);    // 设置图标可以拖动
        baiduMap.addOverlay(options);
        // 添加一个向北的标志
        options = new MarkerOptions().icon(icon)
                .title("向北")
                .position(new LatLng(beidajiePos.latitude + 0.001, beidajiePos.longitude))
                .draggable(true);
        baiduMap.addOverlay(options);

        // 添加一个向东的标志
        options = new MarkerOptions().icon(icon)
                .title("向东")
                .position(new LatLng(beidajiePos.latitude, beidajiePos.longitude + 0.001))
                .draggable(true);
        baiduMap.addOverlay(options);

        // 添加一个向西南的标志
        options = new MarkerOptions().icon(icon)
                .title("向西南")
                .position(new LatLng(beidajiePos.latitude - 0.001, beidajiePos.longitude - 0.001))
                .draggable(true);
        baiduMap.addOverlay(options);
    }
}
