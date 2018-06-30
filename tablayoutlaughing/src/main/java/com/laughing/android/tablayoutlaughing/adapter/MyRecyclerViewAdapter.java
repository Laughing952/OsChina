package com.laughing.android.tablayoutlaughing.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.laughing.android.tablayoutlaughing.LitePalBean.Student;
import com.laughing.android.tablayoutlaughing.R;
import com.laughing.android.tablayoutlaughing.activity.UpdateStudentActivity;
import com.laughing.android.tablayoutlaughing.holder.MyRecyclerViewHolder;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * 作者：Laughing on 2017/9/1 21:08
 * 邮箱：719240226@qq.com
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {

    private List<Student> list;
    private Context mContext;

    public MyRecyclerViewAdapter(List<Student> list, Context context) {
        this.list = list;
        this.mContext = context;
    }

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item, null);
        return new MyRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        final Student student = list.get(position);
        holder.mTvNameItem.setText(student.getName());
        String type = student.getType();
        switch (type) {
            case "0":
                type = "全托";
                break;
            case "1":
                type = "午托";
                break;
            case "2":
                type = "晚托";
                break;

            default:

                break;
        }

        String sex = student.getSex();
        switch (sex) {
            case "0":
                sex = "女";
                break;
            case "1":
                sex = "男";
                break;
            case "2":
                sex = "待确定";
                break;
            default:

                break;
        }
        String grade = student.getGrade();
        switch (grade) {
            case "0":
                grade = "删除";
                break;
            case "1":
                grade = "删除";
                break;
            case "2":
                grade = "删除";
                break;
            case "3":
                grade = "删除";
                break;
            case "4":
                grade = "删除";
                break;
            case "5":
                grade = "删除";
                break;

            default:

                break;
        }
        holder.tv_delete_item.setText(grade);
        holder.tv_sex_item.setText(sex);
//        holder.tv_update_item.setText("修改");
        holder.tv_type_item.setText(type);
        holder.tv_delete_item.setText(grade);
        holder.tv_phone1_item.setText(student.getPhone1());
        holder.tv_phone2_item.setText(student.getPhone2());
        holder.tv_more_item.setText("   " + student.getMore());

        //修改信息
        holder.tv_update_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUtils.showToastPlus(mContext, "修改一下");
                Intent intent = new Intent(mContext, UpdateStudentActivity.class);
                intent.putExtra("student", (Serializable) student);
                mContext.startActivity(intent);
            }
        });

        //设置打电话监听事件
        holder.tv_phone1_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(student.getPhone1())) {

                    callDialogShow(student.getPhone1(), student.getName());
                }
            }


        });
        holder.tv_phone2_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(student.getPhone2())) {

                    callDialogShow(student.getPhone2(), student.getName());
                }

            }
        });
        //删除学生Item监听
        holder.tv_delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtils.showToastPlus(mContext, "delete");
                deleteStudentItem(student.getId(),student.getName());
            }
        });

    }

    /**
     * 打电话对话框
     */
    public void callDialogShow(final String phone, String name) {
        // 构造对话框

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示信息");
        builder.setMessage("是否要拨打电话给 " + name + " 的家长");
        // 更新
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                call(phone);
            }
        });
        // 稍后更新
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }


    /**
     * 删除学生信息条目
     */
    public void deleteStudentItem(final int id,final String name) {
        // 构造对话框

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示信息");
        builder.setMessage("是否要删除 " + name + " 的信息");
        // 更新
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                delete(id);
            }
        });
        // 稍后更新
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Dialog noticeDialog = builder.create();
        noticeDialog.show();
    }

    /**
     * 删除指定名字的学生
     *
     * @param id
     */
    private void delete(int id) {
        DataSupport.deleteAll(Student.class, "id=?", id + "");
    }

    private void call(String phone) {
        try {


            Intent intent;
            intent = new Intent(Intent.ACTION_CALL,
                    Uri.parse("tel:" + phone));
            mContext.startActivity(intent);


        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
