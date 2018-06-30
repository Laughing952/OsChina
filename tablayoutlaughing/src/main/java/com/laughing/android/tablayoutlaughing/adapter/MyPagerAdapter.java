package com.laughing.android.tablayoutlaughing.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.laughing.android.tablayoutlaughing.fragment.TabFragment1;
import com.laughing.android.tablayoutlaughing.fragment.TabFragment2;
import com.laughing.android.tablayoutlaughing.fragment.TabFragment3;
import com.laughing.android.tablayoutlaughing.fragment.TabFragment4;
import com.laughing.android.tablayoutlaughing.fragment.TabFragment5;
import com.laughing.android.tablayoutlaughing.fragment.TabFragment6;

/**
 * 作者：Laughing on 2017/9/2 10:46
 * 邮箱：719240226@qq.com
 */

public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private int nNumOfTabs;
    private Context context;

    public MyPagerAdapter(FragmentManager fm, int nNumOfTabs, Context context) {
        super(fm);
        this.nNumOfTabs = nNumOfTabs;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TabFragment1 tab1 = new TabFragment1(context);
//                TabFragment1 tab1 = new TabFragment1();
                return tab1;
            case 1:
                TabFragment2 tab2 = new TabFragment2(context);
//                TabFragment2 tab2 = new TabFragment2();
                return tab2;
            case 2:
                TabFragment3 tab3 = new TabFragment3(context);
//                TabFragment3 tab3 = new TabFragment3();
                return tab3;
            case 3:
                TabFragment4 tab4 = new TabFragment4(context);
//                TabFragment4 tab4 = new TabFragment4();
                return tab4;
            case 4:
                TabFragment5 tab5 = new TabFragment5(context);
//                TabFragment5 tab5 = new TabFragment5();
                return tab5;
            case 5:
                TabFragment6 tab6 = new TabFragment6(context);
//                TabFragment6 tab6 = new TabFragment6();
                return tab6;

        }
        return null;
    }

    @Override
    public int getCount() {
        return nNumOfTabs;
    }
}
