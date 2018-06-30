package com.laughing.android.myapplication.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.laughing.android.myapplication.R;
import com.laughing.android.myapplication.utils.ToastUtils;
import com.laughing.android.myapplication.utils.UIUtils;

import static com.laughing.android.myapplication.R.id.title_left;

/**
 * 作者：Laughing on 2016/8/14 11:14
 * 邮箱：719240226@qq.com
 */

public class TiXianActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtn_tixian;
    private ImageView mTitle_left;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tixian;
    }

    @Override
    protected void initView(View mContextView) {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        mTitle_left = (ImageView) findViewById(title_left);
        ImageView title_right = (ImageView) findViewById(R.id.title_right);
        mBtn_tixian = (Button) findViewById(R.id.btn_tixian);
        title_tv.setText("提现");
        mTitle_left.setVisibility(View.VISIBLE);
        title_right.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        mTitle_left.setOnClickListener(this);
        mBtn_tixian.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case title_left:
                closeCurrentActivity();
                break;
            case R.id.btn_tixian:
                ToastUtils.showToastPlus(this, "已发送提现请求");
                UIUtils.getHandler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeCurrentActivity();
                    }
                }, 2000);
                break;

            default:

                break;
        }
    }
}
