package com.laughing.android.myrecyclerview.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.laughing.android.myrecyclerview.R;

/**
 * 作者：Laughing on 2017/9/1 21:09
 * 邮箱：719240226@qq.com
 */

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView mTv;
    public CheckBox mCb;

    public MyRecyclerViewHolder(final View itemView) {
        super(itemView);
        mTv = (TextView) itemView.findViewById(R.id.tv);
        mCb = (CheckBox) itemView.findViewById(R.id.cb);


    }

}
