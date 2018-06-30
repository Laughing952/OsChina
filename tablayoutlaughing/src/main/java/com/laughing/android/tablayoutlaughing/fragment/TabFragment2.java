package com.laughing.android.tablayoutlaughing.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.laughing.android.tablayoutlaughing.LitePalBean.Student;
import com.laughing.android.tablayoutlaughing.R;
import com.laughing.android.tablayoutlaughing.adapter.MyRecyclerViewAdapter;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 作者：Laughing on 2017/9/2 10:56
 * 邮箱：719240226@qq.com
 */

public class TabFragment2 extends Fragment {

    private Context mContext;
    private SwipeRefreshLayout mSwipe_refresh_layout;
    private RecyclerView mRecycler_view;
    private boolean isRefresh = true;

    public TabFragment2() {

    }

    public TabFragment2(Context context) {
        this.mContext = context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(mContext, R.layout.fragment1_tab, null);
        initView(view);
        initListener();
        return view;

    }

    private void initListener() {

    }

    private void initView(View view) {
        mSwipe_refresh_layout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        mRecycler_view = (RecyclerView) view.findViewById(R.id.recycler_view);
        //表格布局
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
//        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        //线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler_view.setLayoutManager(linearLayoutManager);
        mRecycler_view.setAdapter(new MyRecyclerViewAdapter(initList(), mContext));
        mSwipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isRefresh) {
                    mRecycler_view.setAdapter(new MyRecyclerViewAdapter(initList(), mContext));
                    mSwipe_refresh_layout.setRefreshing(false);
                }

            }
        });
    }

    private List<Student> initList() {
        List<Student> students = DataSupport.where("grade=?", "1").find(Student.class);
        return students;
    }
}
