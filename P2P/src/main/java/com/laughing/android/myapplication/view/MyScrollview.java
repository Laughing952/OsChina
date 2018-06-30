package com.laughing.android.myapplication.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * 作者：Laughing on 2016/8/6 16:41
 * 邮箱：719240226@qq.com
 */

public class MyScrollview extends ScrollView {
    //要操作的布局
    private View innerView;
    private float y;
    private Rect normal = new Rect();
    private boolean animationFinish = true;//动画是否结束


    public MyScrollview(Context context) {
        super(context);
    }

    public MyScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 加载xml布局结束后的系统回调方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount > 0) {
            innerView = getChildAt(0);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (innerView == null) {
            return super.onTouchEvent(ev);
        } else {
            commonTouchEvent(ev);
            return true;
        }
//        return super.onTouchEvent(ev);

    }

    /**
     * 用来处理触摸事件(自定义touch事件处理)
     *
     * @param ev
     */
    private void commonTouchEvent(MotionEvent ev) {
        if (animationFinish) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    y = ev.getY();

                    break;
                case MotionEvent.ACTION_MOVE:
                    float preY = y == 0 ? ev.getY() : y;
                    float nowY = ev.getY();
                    int distanceY = (int) (preY - nowY);
                    y = nowY;
                    //操作view进行拖动distanceY的一半
                    if (isNeedMove()) {
                        //布局改变位置之前，记录一下正常状态的位置
                        if (normal.isEmpty()) {
                            normal.set(innerView.getLeft(), innerView.getTop(), innerView.getRight(), innerView.getBottom());
                        }
                        innerView.layout(innerView.getLeft(), innerView.getTop() - distanceY / 2, innerView.getRight(), innerView.getBottom() - distanceY / 2);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    y = 0;
                    //布局回滚到原来的位置
                    if (isNeedAnimation()) {
                        animation();
                    }

                    break;
            }
        }

    }

    /**
     * 开启动画
     */
    private void animation() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, 0, normal.top - innerView.getTop());
        ta.setDuration(200);
        ta.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                animationFinish = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                innerView.clearAnimation();//清除动画
                innerView.layout(normal.left, normal.top, normal.right, normal.bottom);
                normal.setEmpty();
                animationFinish = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        innerView.startAnimation(ta);

    }

    /**
     * 判断是否需要回滚
     *
     * @return
     */
    private boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    /**
     * 判断是否需要移动
     *
     * @return
     */
    private boolean isNeedMove() {
        int offset = innerView.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
//        LogUtils.error("getMeasuredHeight:" + innerView.getMeasuredHeight() + "----getHeight:" + getHeight());
//        LogUtils.error("offset:" + android.R.attr.offset + "----scrollY:" + scrollY);
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }
}
