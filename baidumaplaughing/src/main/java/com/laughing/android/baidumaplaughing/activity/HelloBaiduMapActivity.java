package com.laughing.android.baidumaplaughing.activity;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.laughing.android.baidumaplaughing.R;


/**
 * 地图的一般处理
 * 作者：Laughing on 2016/7/27 19:36
 * 邮箱：719240226@qq.com
 */
public class HelloBaiduMapActivity extends BaseMapActivity {

    @Override
    public void init() {

        LinearLayout ll_hello = (LinearLayout) findViewById(R.id.ll_hello);
        ll_hello.setVisibility(View.VISIBLE);
        Button button5 = (Button) findViewById(R.id.bt_5);
        Button button4 = (Button) findViewById(R.id.bt_4);
        Button button3 = (Button) findViewById(R.id.bt_3);
        Button button2 = (Button) findViewById(R.id.bt_2);
        Button button1 = (Button) findViewById(R.id.bt_1);


    }

    public void click(View v) {
        // 5.	更新地图状态
        MapStatusUpdate mapStatusUpdate = null;
        switch (v.getId()) {
            case R.id.bt_1:
                // 		1)	缩小
                mapStatusUpdate = MapStatusUpdateFactory.zoomOut();
                break;
            case R.id.bt_2:
                // 		2)	放大
                mapStatusUpdate = MapStatusUpdateFactory.zoomIn();
                break;
            case R.id.bt_3:
                // 		3)	旋转（0 ~ 360），每次在原来的基础上再旋转30度
                MapStatus currentMapStatus = baiduMap.getMapStatus();    // 获取地图当前的状态
                float rotate = currentMapStatus.rotate + 30;
                Log.i(TAG, "rotate = " + rotate);
                MapStatus mapStatus = new MapStatus.Builder().rotate(rotate).build();
                mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
                break;
            case R.id.bt_4:
                // 		4)	俯仰（0 ~ -45），每次在原来的基础上再俯仰-5度
                currentMapStatus = baiduMap.getMapStatus();    // 获取地图当前的状态
                float overlook = currentMapStatus.overlook - 5;
                Log.i(TAG, "overlook = " + overlook);
                mapStatus = new MapStatus.Builder().overlook(overlook).build();
                mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
                break;
            case R.id.bt_5:
                // 		5)	移动
                mapStatusUpdate = MapStatusUpdateFactory.newLatLng(qianYuYangGuangPos);
                baiduMap.animateMapStatus(mapStatusUpdate, 2000);
                break;

        }
        baiduMap.setMapStatus(mapStatusUpdate);
    }

    /**
     * 这里onkeyDown方法用于使用键盘数字键进行（模拟器可用）
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 5.	更新地图状态
        MapStatusUpdate mapStatusUpdate = null;
        switch (keyCode) {
            case KeyEvent.KEYCODE_1:
                // 		1)	缩小
                mapStatusUpdate = MapStatusUpdateFactory.zoomOut();
                break;
            case KeyEvent.KEYCODE_2:
                // 		2)	放大
                mapStatusUpdate = MapStatusUpdateFactory.zoomIn();
                break;
            case KeyEvent.KEYCODE_3:
                // 		3)	旋转（0 ~ 360），每次在原来的基础上再旋转30度
                MapStatus currentMapStatus = baiduMap.getMapStatus();    // 获取地图当前的状态
                float rotate = currentMapStatus.rotate + 30;
                Log.i(TAG, "rotate = " + rotate);
                MapStatus mapStatus = new MapStatus.Builder().rotate(rotate).build();
                mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);

                break;
            case KeyEvent.KEYCODE_4:
                // 		4)	俯仰（0 ~ -45），每次在原来的基础上再俯仰-5度
                currentMapStatus = baiduMap.getMapStatus();    // 获取地图当前的状态
                float overlook = currentMapStatus.overlook - 5;
                Log.i(TAG, "overlook = " + overlook);
                mapStatus = new MapStatus.Builder().overlook(overlook).build();
                mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);

                break;
            case KeyEvent.KEYCODE_5:
                // 		5)	移动
                mapStatusUpdate = MapStatusUpdateFactory.newLatLng(czPos);
                baiduMap.animateMapStatus(mapStatusUpdate, 2000);
                return super.onKeyDown(keyCode, event);
        }
        baiduMap.setMapStatus(mapStatusUpdate);
        return super.onKeyDown(keyCode, event);
    }


}