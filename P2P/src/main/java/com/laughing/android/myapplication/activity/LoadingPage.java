package com.laughing.android.myapplication.activity;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.laughing.android.myapplication.R;
import com.laughing.android.myapplication.common.AppNetConfig;
import com.laughing.android.myapplication.utils.UIUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 作者：Laughing on 2017/8/11 07:58
 * 邮箱：719240226@qq.com
 */

public abstract class LoadingPage extends FrameLayout {
    private static final int PAGE_LOADING_STATE = 1;//正在加载状态
    private static final int PAGE_ERROR_STATE = 2;//错误状态
    private static final int PAGE_EMPTY_STATE = 3;//没有数据状态
    private static final int PAGE_SUCCESS_STATE = 4;//成功状态
    private int PAGE_CURRENT_STATE = 1;
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;
    private LayoutParams lp;
    private Context mConext;//这里要用Activity的context，若使用Application的context对象，则Indicater样式会无法显示
    private ResultState resultState = null;//枚举类的对象
    private AsyncHttpClient client = new AsyncHttpClient();

    public LoadingPage(@NonNull Context context) {
//        super(context);
        this(context, null);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }

    public LoadingPage(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mConext = context;
        init();
    }

    /**
     * 初始化几种页面
     */
    private void init() {
        lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (loadingView == null) {
            loadingView = UIUtils.getXmlView(R.layout.page_loading);
            addView(loadingView, lp);
        }
        if (errorView == null) {
            errorView = UIUtils.getXmlView(R.layout.page_error);
            addView(errorView, lp);
        }
        if (emptyView == null) {
            emptyView = UIUtils.getXmlView(R.layout.page_empty);
            addView(emptyView, lp);
        }

        showSafePage();
    }

    /**
     * 主线程更新UI
     */
    private void showSafePage() {
        UIUtils.runOnUIThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });

    }

    /**
     * 根据不同状态显示不同页面信息
     */
    private void showPage() {
        loadingView.setVisibility(PAGE_CURRENT_STATE == PAGE_LOADING_STATE ? View.VISIBLE : View.GONE);
        emptyView.setVisibility(PAGE_CURRENT_STATE == PAGE_EMPTY_STATE ? View.VISIBLE : View.GONE);
        errorView.setVisibility(PAGE_CURRENT_STATE == PAGE_ERROR_STATE ? View.VISIBLE : View.GONE);
        if (successView == null) {
            successView = View.inflate(mConext, LayoutId(), null);//因为在fragment中添加Indicater的三个孩子子样式会显示异常
            addView(successView, lp);
        }
        successView.setVisibility(PAGE_CURRENT_STATE == PAGE_SUCCESS_STATE ? View.VISIBLE : View.GONE);

    }

    public void show() {
        //状态归位
        if (PAGE_CURRENT_STATE != PAGE_LOADING_STATE) {
            PAGE_CURRENT_STATE = PAGE_LOADING_STATE;
        }
        //处理不需要发送请求来决定界面的情况
        String url = url();
        if (TextUtils.isEmpty(url)) {
            resultState = ResultState.SUCCESS;
            resultState.setContent("");
            loadPage();
        } else {
            client.post(AppNetConfig.INDEX, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(String content) {
//                    content=null;//测试没有数据显示页面效果
                    if (TextUtils.isEmpty(content)) {
                        //没有数据
                        resultState = ResultState.EMPTY;
                        resultState.setContent("");

                    } else {
                        //有数据
                        resultState = ResultState.SUCCESS;
                        resultState.setContent(content);
                    }
                    loadPage();
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                    resultState = ResultState.ERROR;
                    resultState.setContent("");
                    loadPage();
                }
            });

        }

    }

    private void loadPage() {
        switch (resultState) {
            case ERROR:
                //当前状态设置为2，显示错误界面
                PAGE_CURRENT_STATE = 2;
                break;
            case EMPTY:
                //当前状态设置为3，显示空界面
                PAGE_CURRENT_STATE = 3;
                break;
            case SUCCESS:
                //当前状态设置为4，显示成功界面
                PAGE_CURRENT_STATE = 4;
                break;

        }
        showSafePage();
        if (PAGE_CURRENT_STATE == 4) {
            if (PAGE_CURRENT_STATE == 4) {
                OnSuccess(resultState, successView);
            }
        }

    }

    /**
     * 加载成功之后的页面各不相同，所以由子类来具体实现
     *
     * @return
     */
    protected abstract int LayoutId();

    /**
     * 请求数据的网址 子类来具体实现
     *
     * @return
     */
    protected abstract String url();

    /**
     * 请求参数 子类来具体实现
     *
     * @return
     */
    protected abstract RequestParams params();

    /**
     * 请求成功之后的方法工资类实现
     *
     * @param resultState 状态
     * @param successView 成功后的View
     */
    protected abstract void OnSuccess(ResultState resultState, View successView);

    /**
     * 定义一个枚举类
     */
    public enum ResultState {
        ERROR(2), EMPTY(3), SUCCESS(4);

        private int state;
        private String content;

        ResultState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
