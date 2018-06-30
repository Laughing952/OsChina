package com.laughing.android.myapplication.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * 作者：Laughing on 2017/8/15 11:14
 * 邮箱：719240226@qq.com
 */

public class AlawysTextView extends android.support.v7.widget.AppCompatTextView {
    public AlawysTextView(Context context) {
        super(context);
    }

    public AlawysTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AlawysTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;//让textView默认获取焦点
    }
}
