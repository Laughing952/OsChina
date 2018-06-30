package com.laughing.android.tablayoutlaughing.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.laughing.android.tablayoutlaughing.R;


/**
 * 作者：Laughing on 2017/9/1 21:09
 * 邮箱：719240226@qq.com
 */

public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView mTvNameItem, tv_delete_item, tv_phone1_item,
            tv_phone2_item, tv_more_item, tv_update_item, tv_sex_item, tv_type_item;

    public MyRecyclerViewHolder(final View itemView) {
        super(itemView);
        mTvNameItem = (TextView) itemView.findViewById(R.id.tv_name_item);//姓名
        tv_sex_item = (TextView) itemView.findViewById(R.id.tv_sex_item);//性别
        tv_type_item = (TextView) itemView.findViewById(R.id.tv_type_item);//性别
        tv_update_item = (TextView) itemView.findViewById(R.id.tv_update_item);//修改
        tv_delete_item = (TextView) itemView.findViewById(R.id.tv_delete_item);//删除
        tv_phone1_item = (TextView) itemView.findViewById(R.id.tv_phone1_item);//联系电话1
        tv_phone2_item = (TextView) itemView.findViewById(R.id.tv_phone2_item);//联系电话2
        tv_more_item = (TextView) itemView.findViewById(R.id.tv_more_item);//备注

    }

}
