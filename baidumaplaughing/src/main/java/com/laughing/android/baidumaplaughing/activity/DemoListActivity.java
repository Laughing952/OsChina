package com.laughing.android.baidumaplaughing.activity;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.baidu.mapapi.SDKInitializer;
import com.laughing.android.baidumaplaughing.sdkdemo.BNDemoMainActivity;
import com.laughing.android.baidumaplaughing.util.ToastUtils;


/**
 * 作者：Laughing on 2016/7/27 19:36
 * 邮箱：719240226@qq.com
 */

public class DemoListActivity extends ListActivity {
    private BroadcastReceiver mReceiver;
    private IntentFilter mFilter;

    private ClassAndName[] datas = {
            new ClassAndName(HelloBaiduMapActivity.class, "HelloBaiduMap"),
            new ClassAndName(MapLayerActivity.class, "地图三种模式"),
            new ClassAndName(CircleOverlayActivity.class, "圆形覆盖物"),
            new ClassAndName(TextOverlayActivity.class, "文字覆盖物"),
            new ClassAndName(MarkerOverlayActivity.class, "标志覆盖物"),
            new ClassAndName(SearchInBoundActivity.class, "在边界范围内搜索"),
            new ClassAndName(SearchInCityActivity.class, "在城市范围内搜索"),
            new ClassAndName(SearchInNearbyActivity.class, "在固定点周边搜索"),
            new ClassAndName(DrivingSearchActivity.class, "驾驶路线"),
            new ClassAndName(TransitSearchActivity.class, "公交，地铁路线"),
            new ClassAndName(WalkingSearchActivity.class, "步行路线"),
            new ClassAndName(LocationActivity.class, "定位"),
            new ClassAndName(NavigationActivity.class, "导航"),
            new ClassAndName(BNDemoMainActivity.class, "百度原生导航"),
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerSDKCheckReceiver();//注册广播
        ArrayAdapter<ClassAndName> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, datas);
        setListAdapter(adapter);
    }

    //复写ListActivity的点击事件（此处不用设置点击事件，直接复写父类的即可）
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        // 取出单击位置的ClassAndName
        ClassAndName classAndName = (ClassAndName) l.getItemAtPosition(position);
        startActivity(new Intent(this, classAndName.clazz));
        super.onListItemClick(l, v, position, id);
    }

    private void registerSDKCheckReceiver() {
        //检测广播意图是否为百度地图权限验证失败的广播
        //没有联网的广播
        //百度Android SDK密钥验证成功
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR.equals(action)) {
                    //检测广播意图是否为百度地图权限验证失败的广播
                    ToastUtils.showToastPlus(DemoListActivity.this, "网络错误");
                } else if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR.equals(action)) {
                    //没有联网的广播
                    ToastUtils.showToastPlus(DemoListActivity.this, "key验证失败");
                } else if (SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK.equals(action)) {
                    //百度Android SDK密钥验证成功
                    ToastUtils.showToastPlus(DemoListActivity.this, "OK");
                }
            }
        };
        mFilter = new IntentFilter();
        // 监听网络错误
        mFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        // 监听百度地图sdk 的key是否正确
        mFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        mFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        registerReceiver(mReceiver, mFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private class ClassAndName {
        /**
         * 用于保存Activity的字节码
         */
        private Class<?> clazz;
        /**
         * 用于保存Activity对应的名字
         */
        private String name;

        public ClassAndName(Class<?> clazz, String name) {
            this.clazz = clazz;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
