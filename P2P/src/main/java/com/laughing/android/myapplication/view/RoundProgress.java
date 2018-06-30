package com.laughing.android.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.laughing.android.myapplication.R;

/**
 * 作者：Laughing on 2016/8/10 12:00
 * 邮箱：719240226@qq.com
 */

public class RoundProgress extends View {

    private Paint mPaint;
    private int mRoundColor;
    private int mRoundProgressColor;
    private int mTextColor;
    private float mTextSize;
    private float mRoundWidth;
    private TypedArray mTypedArray;
    private int progress = 50;
    private int max = 100;

    public RoundProgress(Context context) {
//        super(context);
        this(context, null);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }

    public RoundProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取attrs.xml中的自定义属性
        mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundProgress);
        //圆环的颜色
        mRoundColor = mTypedArray.getColor(R.styleable.RoundProgress_roundColor, Color.RED);
        //圆环进度的颜色
        mRoundProgressColor = mTypedArray.getColor(R.styleable.RoundProgress_roundProgressColor, Color.GREEN);
        //中间进度百分比文字字符串的颜色
        mTextColor = mTypedArray.getColor(R.styleable.RoundProgress_textColor, Color.GREEN);
        //中间进度百分比的字符串的字体大小
        mTextSize = mTypedArray.getDimension(R.styleable.RoundProgress_textSize, 15);
        //圆环的宽度
        mRoundWidth = mTypedArray.getDimension(R.styleable.RoundProgress_roundWidth, 5);
        mTypedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //第一步：绘制一个最外层的圆
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mRoundColor);
        mPaint.setStrokeWidth(mRoundWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        int center = getWidth() / 2;
        int radius = (int) (center - mRoundWidth / 2);
        canvas.drawCircle(center, center, radius, mPaint);

        //第二步：绘制中心文字
        mPaint.setColor(mTextColor);
        mPaint.setStrokeWidth(4);
        mPaint.setTextSize(mTextSize);
        float textWidth = mPaint.measureText(progress + "%");
        canvas.drawText(progress + "%", center - textWidth / 2, center + mTextSize / 2, mPaint);

        //第三步：画进度条
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);
        mPaint.setColor(mRoundProgressColor);
        mPaint.setStrokeWidth(mRoundWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        /**
         * 参数解释：
         * oval：绘制弧形圈所包含的矩形范围轮廓
         * 0：开始的角度
         * 360 * progress / max：扫描过的角度
         * false：是否包含圆心
         * paint：绘制弧形时候的画笔
         */
        canvas.drawArc(oval, 0, 360*progress/max, false, mPaint);
    }

    public void setProgress(int progress) {
        this.progress = progress;
        if (progress >= 100) {
            this.progress = 100;
        }
        postInvalidate();//重新绘制进度图
    }
}
