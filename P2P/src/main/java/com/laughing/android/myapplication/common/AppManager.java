package com.laughing.android.myapplication.common;

import android.app.Activity;

import java.util.Stack;

/**
 * 作者：Laughing on 2016/8/6 12:39
 * 邮箱：719240226@qq.com
 * <p/>
 * AppManager设计成单例模式
 * <p/>
 * 统一app程序当中所有的activity栈管理
 * <p/>
 * 添加、删除指定、删除当前、删除所有、求栈大小....
 */

public class AppManager {
    public static AppManager appManager = new AppManager();
    public static Stack<Activity> activityStack = new Stack<>();

    private AppManager() {
    }

    public static AppManager getInstance() {
        if (appManager == null) {
            appManager = new AppManager();
        }
        return appManager;
    }

    public static void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public void removeActivity(Activity activity) {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity1 = activityStack.get(i);
            if (activity1.getClass().equals(activity.getClass())) {
                activity1.finish();
                activityStack.remove(activity1);
                break;
            }
        }
    }

    public void removeCurrentActivity() {
        Activity activity = activityStack.lastElement();
        activity.finish();
        activityStack.remove(activity);
    }

    public void removeAll() {
        for (int i = activityStack.size() - 1; i >= 0; i--) {
            Activity activity1 = activityStack.get(i);
            activity1.finish();
            activityStack.remove(activity1);
        }
    }

    public int getSize() {
        return activityStack.size();
    }
}
