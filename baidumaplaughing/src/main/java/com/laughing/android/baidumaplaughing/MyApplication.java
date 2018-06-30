package com.laughing.android.baidumaplaughing;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;


/**
 * 作者：Laughing on 2017/7/25 17:20
 * 邮箱：719240226@qq.com
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
    }

}
