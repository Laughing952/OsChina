package com.laughing.android.baidumaplaughing.activity;

import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.Stroke;

/**
 * 添加一个圆形覆盖物到指定位置
 * 作者：Laughing on 2016/7/28 19:40
 * 邮箱：719240226@qq.com
 */

public class CircleOverlayActivity extends BaseMapActivity {
    @Override
    public void init() {
        CircleOptions options = new CircleOptions();// 创建一个圆形覆盖物的参数
        options.center(beidajiePos) // 圆心
                .radius(500)    // 半径
                .stroke(new Stroke(5, 0x55ff0000))  // 线条宽度、颜色
                .fillColor(0x5500ff00); // 圆的填充颜色
        baiduMap.addOverlay(options);   // 添加一个覆盖物
    }
}
