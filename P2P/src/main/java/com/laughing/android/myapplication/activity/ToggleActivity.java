package com.laughing.android.myapplication.activity;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.laughing.android.myapplication.R;
import com.laughing.android.myapplication.utils.ToastUtils;


/**
 * Created by laughign on 2015/12/20.
 */
public class ToggleActivity extends BaseActivity {

    private ToggleButton tg;
    private ImageView mTitle_left;

    @Override
    protected void initData() {
        tg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //密码已经开启
//                    UIUtils.Toast("密码已经开启", false);
                    ToastUtils.showToastLength(ToggleActivity.this, "密码已经开启", false);
                } else {
                    ToastUtils.showToastLength(ToggleActivity.this, "密码已经关闭", false);

//                    UIUtils.Toast("密码已经关闭", false);
                }
            }
        });

    }

    @Override
    protected void initView(View mContextView) {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        mTitle_left = (ImageView) findViewById(R.id.title_left);
        ImageView title_right = (ImageView) findViewById(R.id.title_right);
        title_tv.setText("饼状图示例");
        mTitle_left.setVisibility(View.VISIBLE);
        title_right.setVisibility(View.INVISIBLE);
        tg = (ToggleButton) findViewById(R.id.tg);
    }

    @Override
    protected void initListener() {
        mTitle_left.setOnClickListener(new View.OnClickListener() {//回退按钮
            @Override
            public void onClick(View v) {
                closeCurrentActivity();
            }
        });
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_toggle;
    }


}
