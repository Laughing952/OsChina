package com.laughing.android.myrecyclerview.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.laughing.android.myrecyclerview.R;
import com.laughing.android.myrecyclerview.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context mContext = this;
    private RecyclerView mRecycler_view;
    private SwipeRefreshLayout mSwipe_refresh_layout;
    private MenuInflater mi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mi = new MenuInflater(this);
        initView();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//设置方向
//        mRecycler_view.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecycler_view.setLayoutManager(gridLayoutManager);
        mRecycler_view.setAdapter(new MyRecyclerViewAdapter(initList(), mContext));

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

        return true;
    }

    private List<String> initList() {
        List<String> list = new ArrayList<>();
        list.add("一年级");
        list.add("贾雨璇");
        list.add("衡思远");
        list.add("申梦星");
        list.add("孟雨瑶");
        list.add("刘高宇");
        list.add("王滢伟");
        list.add("袁浩宇");
        list.add("二年级");
        list.add("张嘉豪");
        list.add("张俊豪");
        list.add("王若诚");
        list.add("王诗琪");
        list.add("程勃杰");
        list.add("贺颖鑫");
        list.add("郝芸责");
        list.add("张怡涵");

        return list;
    }


    private void initView() {
        mRecycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mSwipe_refresh_layout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
    }

}
