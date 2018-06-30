package com.laughing.android.baidumaplaughing.activity;

import android.view.View;
import android.widget.LinearLayout;

import com.baidu.mapapi.map.BaiduMap;
import com.laughing.android.baidumaplaughing.R;

/**
 * 地图模式
 * 作者：Laughing on 2017/6/27 20:32
 * 邮箱：719240226@qq.com
 */

public class MapLayerActivity extends BaseMapActivity {
    private LinearLayout ll_content;

    @Override

    public void init() {
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        ll_content.setVisibility(View.VISIBLE);
    }

    public void click(View v) {
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);//每次先把地图状态清空，在设置
        switch (v.getId()) {
            case R.id.bt_normal:
                //普通地图
                baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                baiduMap.setTrafficEnabled(false);
                break;
            case R.id.bt_traffic:
                //交通地图
                baiduMap.setTrafficEnabled(true);

                break;
            case R.id.bt_satellite:
                //卫星地图
                baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                baiduMap.setTrafficEnabled(false);

                break;
        }

    }
}
