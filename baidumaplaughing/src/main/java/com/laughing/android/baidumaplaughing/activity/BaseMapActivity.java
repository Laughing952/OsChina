package com.laughing.android.baidumaplaughing.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.laughing.android.baidumaplaughing.R;
import com.laughing.android.baidumaplaughing.util.LogUtils;
import com.laughing.android.baidumaplaughing.util.ToastUtils;

/**
 * 用来初始化地图的基类
 * Created by Laughing on 2016/7/27.
 */
public abstract class BaseMapActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    /**
     * 黑马坐标（北京市海淀区东北旺南路45号）
     */
    protected LatLng hmPos = new LatLng(40.050513, 116.30361);
    /**
     * 传智坐标
     */
    protected LatLng czPos = new LatLng(40.065817, 116.349902);
    //西安南门坐标
    protected LatLng nanMenPos = new LatLng(34.256965, 108.952705);
    //腾飞创业中心坐标
    protected LatLng tengFeiPos = new LatLng(34.232833, 108.883272);
    //北大街坐标
    protected LatLng beidajiePos = new LatLng(34.276645, 108.953938);
    //芊域阳光坐标
    protected LatLng qianYuYangGuangPos = new LatLng(34.34268, 108.830772);

    protected MapView mMapView;
    protected BaiduMap baiduMap;

    //加final是为了防止子类重写onCreate
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base_map);
        setContentView(R.layout.activity_base_map);//获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        initMapState();
        init();
    }

    /**
     * 让子类进行初始化方法
     */
    public abstract void init();

    /**
     * 关于地图的基本操作
     */
    private void initMapState() {
        // 获取地图控制器
        baiduMap = mMapView.getMap();
        // 1.	隐藏缩放按钮、比例尺
//        mMapView.showZoomControls(false);// 隐藏缩放按钮，默认是显示的
//        mMapView.showScaleControl(false);// 隐藏比例按钮，默认是显示的
        // 2.	获取获取最小（3）、最大缩放级别（20）
        float minZoomLevel = baiduMap.getMinZoomLevel();// 获取地图最小缩放级别
        float maxZoomLevel = baiduMap.getMaxZoomLevel();// 获取地图最大缩放级别
        LogUtils.LOGE(TAG, "minZoomLevel" + minZoomLevel);
        LogUtils.LOGE(TAG, "maxZoomLevel" + maxZoomLevel);
        // 3.	设置地图中心点为黑马
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(beidajiePos);
        baiduMap.setMapStatus(mapStatusUpdate);

        // 4.	设置地图缩放为15
        mapStatusUpdate = MapStatusUpdateFactory.zoomTo(15);
        baiduMap.setMapStatus(mapStatusUpdate);
        // 6.	获取地图Ui控制器：隐藏指南针
//        UiSettings uiSettings = baiduMap.getUiSettings();
//        uiSettings.setCompassEnabled(false);

    }

    /**
     * 在屏幕中央显示一个Toast
     *
     * @param text
     */
    public void showToast(CharSequence text) {
        ToastUtils.showToastPlus(this, text);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
}
