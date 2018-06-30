package com.laughing.android.myrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.laughing.android.myrecyclerview.R;
import com.laughing.android.myrecyclerview.holder.MyRecyclerViewHolder;
import com.laughing.android.myrecyclerview.util.ToastUtils;

import java.util.List;

/**
 * 作者：Laughing on 2017/9/1 21:08
 * 邮箱：719240226@qq.com
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {

    private List<String> list;
    private Context mContext;

    public MyRecyclerViewAdapter(List<String> list,Context context) {
        this.list = list;
        mContext=context;
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item, null);
        return new MyRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        final String name = list.get(position);
        holder.mTv.setText(name);
        holder.mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showToastPlus(mContext,name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
