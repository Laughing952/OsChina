package com.laughing.android.baidumaplaughing.activity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.laughing.android.baidumaplaughing.R;
import com.laughing.android.baidumaplaughing.util.LogUtils;

import java.util.List;

/**
 * 定位
 * 作者：Laughing on 2017/7/30 09:52
 * 邮箱：719240226@qq.com
 */

public class LocationActivity extends BaseMapActivity {
    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    protected double mLatitude;
    protected double mLongitude;
    protected LinearLayout mLl_content;

    @Override
    public void init() {

        initUI();
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        //初始化定位信息
        initLocation();
        baiduMap.setMyLocationEnabled(true);    // 开启定位图层
        setMyLocationConfigeration(MyLocationConfiguration.LocationMode.COMPASS);
        mLocationClient.start();    // 开始定位

    }


    /**
     * 用来显示按钮控制模式:罗盘，普通，跟随（这里复用地图显示模式的三个按钮）
     */
    protected void initUI() {
        mLl_content = (LinearLayout) findViewById(R.id.ll_content);
        mLl_content.setVisibility(View.VISIBLE);
        Button bt_normal = (Button) findViewById(R.id.bt_normal);
        bt_normal.setText("普通");
        Button bt_traffic = (Button) findViewById(R.id.bt_traffic);
        bt_traffic.setText("罗盘");
        Button bt_satellite = (Button) findViewById(R.id.bt_satellite);
        bt_satellite.setText("跟随");
    }

    public void click(View v) {
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);//每次先把地图状态清空，在设置
        switch (v.getId()) {
            case R.id.bt_normal:
                // 普通态： 更新定位数据时不对地图做任何操作
                setMyLocationConfigeration(MyLocationConfiguration.LocationMode.NORMAL);
                break;
            case R.id.bt_traffic:
                // 罗盘态，显示定位方向圈，保持定位图标在地图中心
                setMyLocationConfigeration(MyLocationConfiguration.LocationMode.COMPASS);

                break;
            case R.id.bt_satellite:
                // 跟随态，保持定位图标在地图中心
                setMyLocationConfigeration(MyLocationConfiguration.LocationMode.FOLLOWING);

                break;
        }

    }

    /**
     * 设置定位配置信息
     *
     * @param mode 传入的模式： NORMAL,普通
     *             FOLLOWING,跟随
     *             COMPASS;罗盘
     */
    private void setMyLocationConfigeration(MyLocationConfiguration.LocationMode mode) {
//        MyLocationConfiguration.LocationMode var1;//以作为方法参数传进来了
        boolean enableDirection = true;    // 设置允许显示方向
        BitmapDescriptor customMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);    // 自定义定位的图标
        MyLocationConfiguration configuration = new MyLocationConfiguration(mode, enableDirection, customMarker);
//        baiduMap.setMyLocationConfigeration(configuration);
        baiduMap.setMyLocationConfiguration(configuration);
    }


    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备

        option.setCoorType("bd09ll");
        //可选，默认gcj02，设置返回的定位结果坐标系

        int span = 3000;
        option.setScanSpan(span);
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的

        option.setIsNeedAddress(true);
        //可选，设置是否需要地址信息，默认不需要

        option.setOpenGps(true);
        //可选，默认false,设置是否使用gps

        option.setLocationNotify(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果

        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”

        option.setIsNeedLocationPoiList(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到

        option.setIgnoreKillProcess(false);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死

        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集

        option.setEnableSimulateGps(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要

        mLocationClient.setLocOption(option);
    }


    public class MyLocationListener implements BDLocationListener {


        @Override
        public void onReceiveLocation(BDLocation location) {
            /**
             * 处理定位结果显示到地图上
             */
            if (location != null) {
                MyLocationData.Builder builder = new MyLocationData.Builder();
                builder.accuracy(location.getRadius());// 设置方向
                builder.direction(location.getDirection());// 设置精度
                builder.latitude(location.getLatitude());// 设置纬度
                builder.longitude(location.getLongitude());// 设置经度
                // 获得纬度
                mLatitude = location.getLatitude();
                // 获得经度
                mLongitude = location.getLongitude();
                MyLocationData locationData = builder.build();
                baiduMap.setMyLocationData(locationData);// 把定位数据显示到地图上

            }

            //获取定位结果
            StringBuffer sb = new StringBuffer(256);

            sb.append("time : ");
            sb.append(location.getTime());    //获取定位时间

            sb.append("\nerror code : ");
            sb.append(location.getLocType());    //获取类型类型

            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());    //获取纬度信息

            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());    //获取经度信息

            sb.append("\nradius : ");
            sb.append(location.getRadius());    //获取定位精准度

            if (location.getLocType() == BDLocation.TypeGpsLocation) {

                // GPS定位结果
                sb.append("\nspeed : ");
                sb.append(location.getSpeed());    // 单位：公里每小时

                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());    //获取卫星数

                sb.append("\nheight : ");
                sb.append(location.getAltitude());    //获取海拔高度信息，单位米

                sb.append("\ndirection : ");
                sb.append(location.getDirection());    //获取方向信息，单位度

                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\ndescribe : ");
                sb.append("gps定位成功");

            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {

                // 网络定位结果
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());    //获取地址信息

                sb.append("\noperationers : ");
                sb.append(location.getOperators());    //获取运营商信息

                sb.append("\ndescribe : ");
                sb.append("网络定位成功");

            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {

                // 离线定位结果
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");

            } else if (location.getLocType() == BDLocation.TypeServerError) {

                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");

            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {

                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");

            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {

                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");

            }

            sb.append("\nlocationdescribe : ");
            sb.append(location.getLocationDescribe());    //位置语义化信息

            List<Poi> list = location.getPoiList();    // POI数据
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
                }
            }

            LogUtils.LOGE(TAG, sb.toString());
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    @Override
    protected void onDestroy() {
        mLocationClient.stop();        // 停止定位
        super.onDestroy();
    }
}
