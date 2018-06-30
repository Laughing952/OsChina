package com.laughing.android.myapplication.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.laughing.android.myapplication.bean.Login;
import com.laughing.android.myapplication.common.AppManager;
import com.laughing.android.myapplication.utils.SharedPreferenceUtil;
import com.loopj.android.http.AsyncHttpClient;

/**
 * 基类继承FragmentActivity主要是因为:MainActivity中要调用getSupportFragmentManager()方法，必须为FragmentActivity;
 * 作者：Laughing on 2016/8/12 22:10
 * 邮箱：719240226@qq.com
 */

public abstract class BaseActivity extends FragmentActivity {

    protected AsyncHttpClient mClient = new AsyncHttpClient();//提供网络请求对象让子类使用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View mContextView = View.inflate(this, getLayoutId(), null);
        setContentView(mContextView);
        AppManager.getInstance().addActivity(this);
        initView(mContextView);
        initData();
        initListener();

    }


    protected abstract int getLayoutId();

    protected abstract void initView(View mContextView);

    protected abstract void initData();

    protected abstract void initListener();

    /**
     * 跳转到目标activity(必须为公共方法，如果是protected，则非子类无法调用)
     *
     * @param clazz  要跳转到的class
     * @param bundle 跳转绑定的数据
     */
    public void gotoActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);

        if (bundle != null) {
            intent.putExtra("param", bundle);
        }
        startActivity(intent);

    }

    /**
     * 保存登录信息
     *
     * @param login
     */
    protected void saveLogin(Login login) {
        SharedPreferenceUtil.setString(this, "UF_ACC", login.UF_ACC);
        SharedPreferenceUtil.setString(this, "UF_AVATAR_URL", login.UF_AVATAR_URL);
        SharedPreferenceUtil.setString(this, "UF_IS_CERT", login.UF_IS_CERT);
        SharedPreferenceUtil.setString(this, "UF_PHONE", login.UF_PHONE);
    }

    /**
     * 登录成功后获取用户信息进行展示
     *
     * @return
     */
    public Login getLogin() {
        Login login = new Login();
        login.UF_ACC = SharedPreferenceUtil.getString(this, "UF_ACC", "");
        login.UF_AVATAR_URL = SharedPreferenceUtil.getString(this, "UF_AVATAR_URL", "");
        login.UF_IS_CERT = SharedPreferenceUtil.getString(this, "UF_IS_CERT", "");
        login.UF_PHONE = SharedPreferenceUtil.getString(this, "UF_PHONE", "");
        return login;
    }

    /**
     * 关闭当前activity
     */
    public void closeCurrentActivity() {
        AppManager.getInstance().removeCurrentActivity();
    }

    /**
     * 退出登录
     */
    public void loginOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("退出登录");
        builder.setMessage("你确定吗？");
        // 退出
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                getSharedPreferences("user_info", MODE_PRIVATE).edit().clear().commit();
                SharedPreferenceUtil.clearData(BaseActivity.this);
                AppManager.getInstance().removeAll();
                gotoActivity(MainActivity.class, null);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

}
