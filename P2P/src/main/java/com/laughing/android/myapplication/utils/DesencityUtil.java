package com.laughing.android.myapplication.utils;

import android.content.Context;

/**
 * px与dp的转化
 * Created by Laughing on 2017/6/19.
 */

public class DesencityUtil {
    /**
     * @param ctx 上下文
     * @param dp  dp
     * @return
     */
    public static int dp2pix(Context ctx, int dp) {
        float density = ctx.getResources().getDisplayMetrics().density;
        int px = (int) (density * dp + 0.5f);// 4.9->4, 4.1->4, 四舍五入
        return px;
    }

    /**
     * @param ctx 上下文
     * @param px  px
     * @return
     */
    public static float pix2dp(Context ctx, int px) {
        float density = ctx.getResources().getDisplayMetrics().density;
        float dp = px / density;
        return dp;
    }

}
