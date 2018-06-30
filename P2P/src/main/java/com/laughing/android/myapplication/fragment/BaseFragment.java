package com.laughing.android.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laughing.android.myapplication.activity.LoadingPage;
import com.laughing.android.myapplication.utils.UIUtils;
import com.loopj.android.http.RequestParams;

/**
 * 作者：Laughing on 2016/8/10 17:38
 * 邮箱：719240226@qq.com
 */

public abstract class BaseFragment extends Fragment {
    protected View mView;//把加载成功的布局返回给子类，让子类进行初始化，子类也可以调用父类getView（）方法，获取view
    private LoadingPage loadingPage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        loadingPage = new LoadingPage(container.getContext()) {
            @Override
            protected int LayoutId() {
                return getLayoutId();
            }

            @Override
            protected String url() {
                return getUrl();
            }

            @Override
            protected RequestParams params() {
                return getParams();
            }

            @Override
            protected void OnSuccess(ResultState resultState, View successView) {
                mView = successView;
                initView();
                initData(resultState.getContent());
            }
        };

        return loadingPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        UIUtils.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        }, 500);
    }

    public void showPage() {
        loadingPage.show();
    }

    protected abstract RequestParams getParams();

    protected abstract String getUrl();//子类实现

    protected abstract int getLayoutId();

    /**
     * 子类获取自身View的方法
     * @return
     */
    public View getView() {
        return mView;
    }

    protected abstract void initView();

    protected abstract void initData(String content);

}
