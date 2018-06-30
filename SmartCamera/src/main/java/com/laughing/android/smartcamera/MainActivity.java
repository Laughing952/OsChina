package com.laughing.android.smartcamera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

/**
 * （EasyN摄像头访问）TUTK公司云平台获得摄像机视频，及控制摄像头的转动
 * Created by Laughing on 2017/7/23.
 */
public class MainActivity extends AppCompatActivity {

    private TextView tvUid;
    private TextView tvPwd;
    private TextView tvName;
    private EditText etName;
    private EditText etPwd;
    private EditText etUid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noTitle();
        setContentView(R.layout.activity_main);
        initView();
    }

    /**
     * 去掉标题栏
     */
    private void noTitle() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().hide();// 隐藏ActionBar
    }

    private void initView() {
        etName = (EditText) findViewById(R.id.et_name);
        tvName = (TextView) findViewById(R.id.tv_name);
        etUid = (EditText) findViewById(R.id.et_uid);
        tvUid = (TextView) findViewById(R.id.tv_uid);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        tvPwd = (TextView) findViewById(R.id.tv_pwd);
    }

    /**
     * 页面跳转到CameraActivity
     *
     * @param v
     */
    public void startConn(View v) {
        String name = etName.getText().toString().trim();
        String uid = etUid.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showToastPlus(this, "名称不能为空");
        } else if (TextUtils.isEmpty(uid) || uid.length() != 20) {
            ToastUtil.showToastPlus(this, "uid不能正确");
        } else if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showToastPlus(this, "密码不能为空");

        } else {
            Intent intent = new Intent(this, CameraActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("uid", uid);
            intent.putExtra("pwd", pwd);
            startActivity(intent);
        }

    }
}
