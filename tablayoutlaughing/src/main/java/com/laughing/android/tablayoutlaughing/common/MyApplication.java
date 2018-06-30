package com.laughing.android.tablayoutlaughing.common;

import android.app.Application;

import org.litepal.LitePalApplication;

/**这里MyApplication可以继承LitePalApplication来实现同样功能
 * 作者：Laughing on 2017/8/15 17:29
 * 邮箱：719240226@qq.com
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(getApplicationContext());

    }
}
