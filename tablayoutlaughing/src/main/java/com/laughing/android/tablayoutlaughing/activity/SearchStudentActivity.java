package com.laughing.android.tablayoutlaughing.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.laughing.android.tablayoutlaughing.LitePalBean.Student;
import com.laughing.android.tablayoutlaughing.R;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 作者：Laughing on 2017/9/12 21:34
 * 邮箱：719240226@qq.com
 */

public class SearchStudentActivity extends AppCompatActivity {
    private Context mContext = this;
    private ImageButton mIbleftsearch;
    private EditText mEtsearch;
    private Button mBtrightsearch;
    private TextView mTvmoresearchitem;
    private TextView mTvtypesearchitem;
    private TextView mTvphone2searchitem;
    private TextView mTvphone1searchitem;
    private TextView mTvdeletesearchitem;
    private TextView mTvupdatesearchitem;
    private TextView mTvsexsearchitem;
    private TextView mTvnamesearchsearchitem;
    private LinearLayout ll_names;
    private ArrayList<Student> mStudents = null;
    private String mText = "";
    private ListView mListView;
    private MyListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("大乐教育    搜索学生");
        setContentView(R.layout.activity_search);
//        mTvmoresearchitem = (TextView) findViewById(R.id.tv_more_search_item);
//        mTvtypesearchitem = (TextView) findViewById(R.id.tv_type_search_item);
//        mTvphone2searchitem = (TextView) findViewById(R.id.tv_phone2_search_item);
//        mTvphone1searchitem = (TextView) findViewById(R.id.tv_phone1_search_item);
//        mTvdeletesearchitem = (TextView) findViewById(R.id.tv_delete_search_item);
//        mTvupdatesearchitem = (TextView) findViewById(R.id.tv_update_search_item);
//        mTvsexsearchitem = (TextView) findViewById(R.id.tv_sex_search_item);
//        mTvnamesearchsearchitem = (TextView) findViewById(R.id.tv_name_search_search_item);
        ll_names = (LinearLayout) findViewById(R.id.ll_names);
        initView();
        initListener();

    }

    private void initListener() {
        mEtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchAndShow();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mBtrightsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAndShow();
            }
        });
    }

    private void searchAndShow() {
        mText = mEtsearch.getText().toString().trim();
        if (!TextUtils.isEmpty(mText)) {
            if (mStudents != null) {
                mStudents.clear();
            }
            mStudents = (ArrayList<Student>) DataSupport.where("name like ?", "%" + mText + "%").find(Student.class);
            mAdapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        mBtrightsearch = (Button) findViewById(R.id.bt_right_search);
        mEtsearch = (EditText) findViewById(R.id.et_search);
        mListView = (ListView) findViewById(R.id.listView);
        mAdapter = new MyListAdapter();
        mListView.setAdapter(mAdapter);
        mListView.setTextFilterEnabled(true);
    }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mStudents == null ? 0 : mStudents.size();
        }

        @Override
        public Object getItem(int position) {
            return mStudents.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_student_list, null);
            }
            ViewHolder holder = ViewHolder.getHolder(convertView);
            final Student student = mStudents.get(position);
            holder.name.setText(student.getName());
            holder.mTvNameItem.setText(student.getName());
            holder.tv_sex_item.setText(student.getName());
            holder.tv_update_item.setText("修改");
            holder.tv_delete_item.setText("删除");
            holder.tv_phone1_item.setText(student.getPhone1());
            holder.tv_phone2_item.setText(student.getPhone2());
            holder.tv_more_item.setText(student.getMore());
            holder.tv_update_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    ToastUtils.showToastPlus(mContext, "修改");
                    Intent intent = new Intent(mContext, UpdateStudentActivity.class);
                    intent.putExtra("student", (Serializable) student);
                    mContext.startActivity(intent);
                }
            });
            holder.tv_delete_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteStudentItem(student.getId(), student.getName());
                }
            });
            holder.tv_phone1_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(student.getPhone1())) {

                        callDialogShow(student.getPhone1(), student.getName());
                    }
                }
            });
            holder.tv_phone2_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(student.getPhone2())) {

                        callDialogShow(student.getPhone2(), student.getName());
                    }
                }
            });
            return convertView;
        }
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
    public void deleteStudentItem(final int id, final String name) {
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

    public static class ViewHolder {
        TextView name;
        public TextView mTvNameItem, tv_delete_item, tv_phone1_item,
                tv_phone2_item, tv_more_item, tv_update_item, tv_sex_item, tv_type_item;

        public ViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.tv_student_name);
            mTvNameItem = (TextView) convertView.findViewById(R.id.tv_name_item);//姓名
            tv_sex_item = (TextView) convertView.findViewById(R.id.tv_sex_item);//性别
            tv_type_item = (TextView) convertView.findViewById(R.id.tv_type_item);//性别
            tv_update_item = (TextView) convertView.findViewById(R.id.tv_update_item);//修改
            tv_delete_item = (TextView) convertView.findViewById(R.id.tv_delete_item);//删除
            tv_phone1_item = (TextView) convertView.findViewById(R.id.tv_phone1_item);//联系电话1
            tv_phone2_item = (TextView) convertView.findViewById(R.id.tv_phone2_item);//联系电话2
            tv_more_item = (TextView) convertView.findViewById(R.id.tv_more_item);//备注
        }

        public static ViewHolder getHolder(View convertView) {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if (holder == null) {
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);

            }
            return holder;
        }
    }

}
