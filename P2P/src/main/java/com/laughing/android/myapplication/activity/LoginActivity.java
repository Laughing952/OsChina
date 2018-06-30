package com.laughing.android.myapplication.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laughing.android.myapplication.R;
import com.laughing.android.myapplication.bean.Login;
import com.laughing.android.myapplication.common.AppNetConfig;
import com.laughing.android.myapplication.utils.MD5Utils;
import com.laughing.android.myapplication.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * 作者：Laughing on 2016/8/12 22:09
 * 邮箱：719240226@qq.com
 */

public class LoginActivity extends BaseActivity {

    private View mView;
    private EditText mLog_ed_mob;
    private EditText mLog_ed_pad;

    @Override
    protected void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(View mContextView) {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        ImageView title_left = (ImageView) findViewById(R.id.title_left);
        ImageView title_right = (ImageView) findViewById(R.id.title_right);
        title_tv.setText("登录");
        title_left.setVisibility(View.VISIBLE);
        title_right.setVisibility(View.INVISIBLE);
        //手机号
        mLog_ed_mob = (EditText) findViewById(R.id.log_ed_mob);
        //密码
        mLog_ed_pad = (EditText) findViewById(R.id.log_ed_pad);
        title_left.setOnClickListener(new View.OnClickListener() {//回退按钮
            @Override
            public void onClick(View v) {
                closeCurrentActivity();
            }
        });

    }

    @Override
    protected void initData() {

    }

    /**
     * xml布局绑定点击监听事件
     *
     * @param view
     */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.log_log_btn://点击登录按钮
                login();
//                ToastUtils.showToastPlus(this,"login");
                break;

            default:

                break;
        }
    }

    private void login() {
        String username = mLog_ed_mob.getText().toString();
        String password = mLog_ed_pad.getText().toString();
        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            RequestParams requestParams = new RequestParams();
            requestParams.put("username", username);
            requestParams.put("password", MD5Utils.MD5(password));
            mClient.post(AppNetConfig.LOGIN,requestParams,new AsyncHttpResponseHandler(){
                @Override
                public void onSuccess(String content) {
                    super.onSuccess(content);
                    ToastUtils.showToastPlus(LoginActivity.this, "onSuccess");
                    JSONObject jsonObject = JSON.parseObject(content);
                    if (jsonObject.getBoolean("success")) {
                        String data = jsonObject.getString("data");
                        Login login = JSON.parseObject(data, Login.class);
                        //保存到sp中
                        saveLogin(login);
                        //跳转到首页去
                        gotoActivity(MainActivity.class, null);
                    }
                }

                @Override
                public void onFailure(Throwable error, String content) {
                    super.onFailure(error, content);
                    ToastUtils.showToastPlus(LoginActivity.this, "网络异常，请稍后重试");
                }
            });
        } else {
            ToastUtils.showToastPlus(this, "手机号或密码为空!");
        }

    }
}
