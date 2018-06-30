package com.laughing.android.baidumaplaughing.activity;

import com.baidu.mapapi.map.TextOptions;
import com.laughing.android.baidumaplaughing.util.DesencityUtil;

/**
 * 文字覆盖物
 * 作者：Laughing on 2016/7/2 19:36
 * 邮箱：719240226@qq.com
 */
public class TextOverlayActivity extends BaseMapActivity {


    @Override
    public void init() {
        TextOptions options = new TextOptions();
        options.position(beidajiePos)    // 位置
                .text("This is my home!")   // 文字内容
                .fontSize(DesencityUtil.dp2pix(this, 20))   // 文字大小
                .fontColor(0xff000000)    // 文字颜色
                .bgColor(0x5500ff00)    // 背景颜色
                .rotate(30);    // 旋转
        baiduMap.addOverlay(options);
//        TextOptions options = new TextOptions();
//        options.position(zhongLouPos)            // 位置
//                .text("This is my home!")            // 文字内容
//                .fontSize(20)            // 文字大小
//                .fontColor(0XFF000000)    // 文字颜色
//                .bgColor(0X55FF0000)    // 背景颜色
//                .rotate(30);            // 旋转
//        baiduMap.addOverlay(options);
    }
}
