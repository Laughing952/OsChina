package com.laughing.android.myapplication.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.laughing.android.myapplication.R;
import com.laughing.android.myapplication.utils.UIUtils;
import com.laughing.android.myapplication.view.randomLayout.StellarMap;

import java.util.Random;

/**
 * 作者：Laughing on 2017/8/11 15:13
 * 邮箱：719240226@qq.com
 */

public class ProductRecommendFragment extends Fragment {
    private String[] datas = new String[]{"超级新手计划", "乐享活系列90天计划", "钱包计划", "30天理财计划(加息2%)", "90天理财计划(加息5%)", "180天理财计划(加息10%)",
            "林业局投资商业经营", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍摄投资", "Java培训老师自己周转", "养猪场扩大经营",
            "旅游公司扩大规模", "阿里巴巴洗钱计划", "铁路局回款计划", "高级白领赢取白富美投资计划"
    };
    private Random random;

    private String[] one = new String[8];

    private String[] two = new String[8];
    private StellarMap mStellarMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_product_recommend);
        initView(view);
        initData();
        return view;
    }

    private void initData() {

        for (int i = 0; i < 8; i++) {
            one[i] = datas[i];
        }

        for (int j = 0; j < 8; j++) {
            two[j] = datas[j + 8];
        }
        random = new Random();
        int padding = UIUtils.dp2px(5);
        mStellarMap.setInnerPadding(padding, padding, padding, padding);//设置内边距
        mStellarMap.setAdapter(new MyAdapter());
        //每组出现的数据组的搭配规则
        mStellarMap.setRegularity(8, 8);
        //设置从哪一组开始做动画操作
        mStellarMap.setGroup(0, true);
    }

    private void initView(View view) {
        mStellarMap = (StellarMap) view.findViewById(R.id.stellarMap);

    }

    private class MyAdapter implements StellarMap.Adapter {

        @Override
        public int getGroupCount() {
            return 2;//组数
        }

        @Override
        public int getCount(int group) {
            return 8;//每组数据总数
        }

        @Override
        public View getView(int group, int position, View convertView) {
            // 测试用的布局，用代码实现
            TextView view = new TextView(getActivity());
            view.setText(getClass().getSimpleName());
            int a = 220 + random.nextInt(35);
            int r = 10 + random.nextInt(180);
            int g = 10 + random.nextInt(180);
            int b = 10 + random.nextInt(180);
            //                view.setTextColor(Color.rgb(r, g, b));
            view.setTextColor(Color.argb(a, r, g, b));
            view.setTextSize(UIUtils.dp2px(8) + random.nextInt(8));//字体大小随机
            if (group == 0) {
                view.setText(one[position]);
            } else if (group == 1) {
                view.setText(two[position]);
            }
            return view;
        }

        @Override
        public int getNextGroupOnPan(int group, float degree) {
            return 0;//不能大于1
        }

        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {
            return 1;
        }
    }
}
