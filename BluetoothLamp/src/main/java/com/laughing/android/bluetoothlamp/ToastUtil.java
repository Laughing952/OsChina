package com.laughing.android.bluetoothlamp;
/**
 * Created by Laughing on 2017/7/24.
 */

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 作者：Administrator on 2017/7/24 16:47
 * 邮箱：xxx@163.com
 */

public class ToastUtil {
    private static Toast toast;

    /**
     * 在屏幕中央显示一个Toast（吐司会轮流显示，第一个显示完毕后才会显示第二个）
     *
     * @param context 上下文
     * @param text    文本内容
     */
    public static void showToast(Context context, CharSequence text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 在屏幕中央显示一个Toast(优化版：第二个吐司会覆盖掉第一个吐司，不会排队等候显示)
     *
     * @param context 上下文
     * @param text    文本内容
     */
    public static void showToastPlus(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(text);
        toast.show();
    }
}
