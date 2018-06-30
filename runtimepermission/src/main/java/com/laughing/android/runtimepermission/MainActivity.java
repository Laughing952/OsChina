package com.laughing.android.runtimepermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

/**
 * 用来动态获取权限demo
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//remove title bar  即隐藏标题栏
        getSupportActionBar().hide();// 隐藏ActionBar
        setContentView(R.layout.activity_main);
    }


    /**
     * xml布局绑定点击监听事件
     *
     * @param view
     */
    public void click(View view) {
        switch (view.getId()) {
            case R.id.make_call:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    //请求权限
                    /**
                     * 10001 请求码
                     */
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 10001);
                } else {
                    //权限已有，直接拨打电话
                    call();
                }
                break;

            default:

                break;
        }
    }

    /**
     * 拨打电话
     */
    private void call() {
        try {
            Intent intent;
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10001:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    Toast.makeText(this, "您拒绝了打电话的权限", Toast.LENGTH_SHORT).show();
                }
                break;

            default:

                break;
        }

    }
}
