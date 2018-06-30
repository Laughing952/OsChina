package com.laughing.android.tablayoutlaughing;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.laughing.android.tablayoutlaughing.activity.AddStudentActivity;
import com.laughing.android.tablayoutlaughing.activity.SearchStudentActivity;
import com.laughing.android.tablayoutlaughing.adapter.MyPagerAdapter;
import com.laughing.android.tablayoutlaughing.util.ToastUtils;

import org.litepal.tablemanager.Connector;

public class MainActivity extends AppCompatActivity {
    private Context mContext = this;
    private TabLayout tabLayout;
    private ViewPager vp_view;
    private MenuInflater mi;
    private MyPagerAdapter mAdapter;

    public Context getActivityContext() {
        return mContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("大乐教育    查看学生信息");
        setContentView(R.layout.activity_main);
        mi = new MenuInflater(this);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("一年级"));
        tabLayout.addTab(tabLayout.newTab().setText("二年级"));
        tabLayout.addTab(tabLayout.newTab().setText("三年级"));
        tabLayout.addTab(tabLayout.newTab().setText("四年级"));
        tabLayout.addTab(tabLayout.newTab().setText("五年级"));
        tabLayout.addTab(tabLayout.newTab().setText("六年级"));
        vp_view = (ViewPager) findViewById(R.id.vp_view);
        mAdapter = new MyPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), this);
        vp_view.setAdapter(mAdapter);
        vp_view.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp_view.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    // 创建Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        mi.inflate(R.menu.file_menu, menu);
        return true;
    }

    // Menu的点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.create_database_student:
                ToastUtils.showToastPlus(this, "数据库已创建");
                SQLiteDatabase database = Connector.getDatabase();//创建数据库
                break;
            case R.id.search_student:
                ToastUtils.showToastPlus(this, "查找");
                startActivity(new Intent(this, SearchStudentActivity.class));
//                startActivity(new Intent(this, SearchViewStudentActivity.class));//SearchView实现学生姓名搜索
                break;
            case R.id.add_student_msg:
                startActivity(new Intent(this, AddStudentActivity.class));
                break;
        }
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkCallPermission();

    }

    private void checkCallPermission() {
        //判断用户是否给了我们打电话的权限
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.CALL_PHONE}, 10001);
            return;
        } else {
            //可以进行打电话操作
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 10001:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(mContext, "可以使用打电话功能", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(mContext, "你拒绝了此权限", Toast.LENGTH_SHORT).show();
                }
                break;

            default:

                break;
        }
    }
}
