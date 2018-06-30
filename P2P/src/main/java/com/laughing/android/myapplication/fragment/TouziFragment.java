package com.laughing.android.myapplication.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.laughing.android.myapplication.R;
import com.laughing.android.myapplication.utils.UIUtils;
import com.loopj.android.http.RequestParams;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;


public class TouziFragment extends BaseFragment {

    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private ViewPager mPager;
    private TabPageIndicator mTab_indictor;//TabPageIndicator使用时要修改样式（在Application中修改主题：使得它符合我们项目要求）

    @Override

    protected RequestParams getParams() {
        return new RequestParams();
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_touzi;
    }

    @Override
    protected void initView() {
        TextView title_tv = (TextView) mView.findViewById(R.id.title_tv);
        ImageView title_left = (ImageView) mView.findViewById(R.id.title_left);
        ImageView title_right = (ImageView) mView.findViewById(R.id.title_right);
        title_tv.setText("我要投资");
        title_left.setVisibility(View.INVISIBLE);
        title_right.setVisibility(View.INVISIBLE);
        mPager = (ViewPager) mView.findViewById(R.id.pager);
        mTab_indictor = (TabPageIndicator) mView.findViewById(R.id.tab_indictor);
    }

    @Override
    protected void initData(String content) {
        initFragment();
        mPager.setAdapter(new MyAdapter(getFragmentManager()));
        mTab_indictor.setViewPager(mPager);

    }

    private void initFragment() {
        ProductListFragment productListFragment = new ProductListFragment();
        ProductRecommendFragment productRecommendFragment = new ProductRecommendFragment();
        ProductHotFragment productHotFragment = new ProductHotFragment();
        mFragments.add(productListFragment);
        mFragments.add(productRecommendFragment);
        mFragments.add(productHotFragment);

    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments == null ? 0 : mFragments.size();
        }

        /**
         * 复写此方法是为了添加标题
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return UIUtils.getStringArr(R.array.touzi_tab)[position];
        }

    }
}
