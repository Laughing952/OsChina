package com.laughing.android.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * SharedPreference中存储的数据方法类
 * Created by Laughing on 2017/6/6.
 */

public class SharedPreferenceUtil {
    /**
     * 获取SharedPreference中存储的数据
     *
     * @param ctx   上下文
     * @param name  key
     * @param value 存储的值
     * @return
     */
    public static void setInt(Context ctx, String name, int value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences("user_info", MODE_PRIVATE).edit();
        editor.putInt(name, value).commit();
    }

    /**
     * @param ctx      上下文
     * @param name     key
     * @param defValue 默认值
     * @return
     */
    public static int getInt(Context ctx, String name, int defValue) {

        SharedPreferences sp = ctx.getSharedPreferences("user_info", MODE_PRIVATE);
        return sp.getInt(name, defValue);
    }

    /**
     * 获取SharedPreference中存储的数据
     *
     * @param ctx   上下文
     * @param name  key
     * @param value 存储的值
     * @return
     */
    public static void setString(Context ctx, String name, String value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences("user_info", MODE_PRIVATE).edit();
        editor.putString(name, value).commit();
    }

    /**
     * @param ctx      上下文
     * @param name     key
     * @param defValue 默认值
     * @return
     */
    public static String getString(Context ctx, String name, String defValue) {

        SharedPreferences sp = ctx.getSharedPreferences("user_info", MODE_PRIVATE);
        return sp.getString(name, defValue);
    }

    /**
     * 获取SharedPreference中存储的数据
     *
     * @param ctx   上下文
     * @param name  key
     * @param value 存储的值
     * @return
     */
    public static void setBoolean(Context ctx, String name, boolean value) {
        SharedPreferences.Editor editor = ctx.getSharedPreferences("user_info", MODE_PRIVATE).edit();
        editor.putBoolean(name, value).commit();
    }

    /**
     * @param ctx      上下文
     * @param name     key
     * @param defValue 默认值
     * @return
     */
    public static boolean getBoolean(Context ctx, String name, Boolean defValue) {

        SharedPreferences sp = ctx.getSharedPreferences("user_info", MODE_PRIVATE);
        return sp.getBoolean(name, defValue);
    }

    public static void clearData(Context ctx) {
        ctx.getSharedPreferences("user_info", MODE_PRIVATE).edit().clear().commit();
    }

}
