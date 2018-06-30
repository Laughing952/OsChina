package com.laughing.android.myapplication.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioButton;

import com.laughing.android.myapplication.R;
import com.laughing.android.myapplication.fragment.HomeFragment;
import com.laughing.android.myapplication.fragment.MeFragment;
import com.laughing.android.myapplication.fragment.MoreFragment;
import com.laughing.android.myapplication.fragment.TouziFragment;

/**
 * 作者：Laughing on 2016/8/10 12:00
 * 邮箱：719240226@qq.com
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {


    private RadioButton rb1, rb2, rb3, rb4;
    private Fragment mHomeFragment, mTouziFragment, mMeFragment, mMoreFragment;
    private android.support.v4.app.FragmentTransaction mFt;

//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView();
//        initView();
//        initListener();
//        initData();
//
//    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(View mContextView) {
        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rb4 = (RadioButton) findViewById(R.id.rb4);

    }

    @Override
    protected void initListener() {
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);
        rb4.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        selectFragment(0);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rb1:
                selectFragment(0);
                break;
            case R.id.rb2:
                selectFragment(1);

                break;
            case R.id.rb3:
                selectFragment(2);

                break;
            case R.id.rb4:
                selectFragment(3);

                break;

            default:

                break;
        }

    }


    /**
     * 用来隐藏Fragment
     */
    private void hideFragment() {
        if (mHomeFragment != null) {
            mFt.hide(mHomeFragment);
        }
        if (mTouziFragment != null) {
            mFt.hide(mTouziFragment);
        }
        if (mMeFragment != null) {
            mFt.hide(mMeFragment);
        }
        if (mMoreFragment != null) {
            mFt.hide(mMoreFragment);
        }

    }

    /**
     * 点击选项卡显示对应的Fragment
     *
     * @param i
     */
    private void selectFragment(int i) {
        FragmentManager fm = getSupportFragmentManager();
        mFt = fm.beginTransaction();
        hideFragment();
        switch (i) {
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    mFt.add(R.id.fl_content, mHomeFragment);
                }
                mFt.show(mHomeFragment);
                break;
            case 1:
                if (mTouziFragment == null) {
                    mTouziFragment = new TouziFragment();
                    mFt.add(R.id.fl_content, mTouziFragment);
                }
                mFt.show(mTouziFragment);
                break;
            case 2:
                if (mMeFragment == null) {
                    mMeFragment = new MeFragment();
                    mFt.add(R.id.fl_content, mMeFragment);
                }
                mFt.show(mMeFragment);
                break;
            case 3:
                if (mMoreFragment == null) {
                    mMoreFragment = new MoreFragment();
                    mFt.add(R.id.fl_content, mMoreFragment);
                }
                mFt.show(mMoreFragment);
                break;

            default:

                break;
        }
        mFt.commit();
    }
}
