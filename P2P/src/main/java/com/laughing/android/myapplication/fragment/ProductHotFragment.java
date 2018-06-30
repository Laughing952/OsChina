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
import com.laughing.android.myapplication.utils.DrawableUtil;
import com.laughing.android.myapplication.utils.UIUtils;
import com.laughing.android.myapplication.view.FlowLayout;

import java.util.Random;

/**
 * 作者：Laughing on 2017/8/11 15:13
 * 邮箱：719240226@qq.com
 */

public class ProductHotFragment extends Fragment {

    private String[] datas = new String[]{"新手计划", "乐享活系列90天计划", "钱包", "30天理财计划(加息2%)",
            "林业局投资商业经营与大捞一笔", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍", "Java培训老师自己周转",
            "HelloWorld", "C++-C-ObjectC-java", "Android vs ios",
            "算法与数据结构", "JNI与NDK", "team working", "新手计划", "乐享活系列90天计划", "钱包", "30天理财计划(加息2%)",
            "林业局投资商业经营与大捞一笔", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍", "Java培训老师自己周转",
            "HelloWorld", "C++-C-ObjectC-java", "Android vs ios", "算法与数据结构", "JNI与NDK", "team working"};
    private Random random;
    private FlowLayout mFlow;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = UIUtils.getXmlView(R.layout.fragment_product_hot);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        random = new Random();
        for (String data : datas) {
            TextView tv = new TextView(getActivity());
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UIUtils.dp2px(10);
            mp.rightMargin = UIUtils.dp2px(10);
            mp.topMargin = UIUtils.dp2px(10);
            mp.bottomMargin = UIUtils.dp2px(10);
            tv.setLayoutParams(mp);
            tv.setText(data);
            int r = 60+random.nextInt(160);
            int g =60+ random.nextInt(160);
            int b = 60+random.nextInt(160);
            tv.setBackground(
                    DrawableUtil.getSelector(DrawableUtil.getDrawable(Color.rgb(r, g, b), UIUtils.dp2px(5)),
                            DrawableUtil.getDrawable(Color.WHITE, UIUtils.dp2px(5))));
            int padding = UIUtils.dp2px(5);
            tv.setPadding(padding, padding, padding, padding);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            mFlow.addView(tv);
        }
    }

    private void initView(View view) {
        mFlow = (FlowLayout) view.findViewById(R.id.flow);

    }
}
