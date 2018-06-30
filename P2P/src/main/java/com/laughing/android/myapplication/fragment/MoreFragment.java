package com.laughing.android.myapplication.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laughing.android.myapplication.R;
import com.loopj.android.http.RequestParams;


public class MoreFragment extends BaseFragment {


    private TextView mTv;

    @Override
    protected RequestParams getParams() {
        return null;
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initView() {
        TextView title_tv = (TextView) mView.findViewById(R.id.title_tv);
        mTv = (TextView) mView.findViewById(R.id.tv);
        ImageView title_left = (ImageView) mView.findViewById(R.id.title_left);
        ImageView title_right = (ImageView) mView.findViewById(R.id.title_right);
        title_tv.setText("更多");
        title_left.setVisibility(View.INVISIBLE);
        title_right.setVisibility(View.INVISIBLE);

        //        mTv.setFocusable(true);
//        mTv.setFocusableInTouchMode(true);
//        mTv.requestFocus();
    }

    @Override
    protected void initData(String content) {


    }


}
