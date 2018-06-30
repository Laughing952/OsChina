package com.laughing.android.myapplication.common;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by laughing on 2015/12/11.
 * 使用时要在application节点中进行配置
 */
public class MyApplication extends Application {

    public static Context context = null;

    public static Handler handler = null;

    public static Thread mainThread = null;

    public static int mainThreadId = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        handler = new Handler();
        mainThread = Thread.currentThread();
        mainThreadId = android.os.Process.myTid();
//        CrashHandler.getInstance().init(this);//处理异常
    }
}
