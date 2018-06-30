package com.laughing.android.myapplication.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**用来代码来实现selector效果代替xml实现（因为xml无法随机背景色）
 * Created by Administrator on 2016/8/19.
 */
public class DrawableUtil {

    /**
     * 定义一个shape资源
     *
     * @param rgb
     * @param corneradius
     * @return
     */
    public static GradientDrawable getDrawable(int rgb, int corneradius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(rgb);
//        gradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
//        gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        gradientDrawable.setGradientType(GradientDrawable.RECTANGLE);
        gradientDrawable.setCornerRadius(corneradius);
        gradientDrawable.setStroke(UIUtils.dp2px(1), rgb);
        return gradientDrawable;
    }

    public static StateListDrawable getSelector(Drawable normalDrawable,Drawable pressDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        //给当前的颜色选择器添加选中图片指向状态，未选中图片指向状态
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, pressDrawable);
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, normalDrawable);
        //设置默认状态
        stateListDrawable.addState(new int[]{}, normalDrawable);
        return stateListDrawable;
    }

}
