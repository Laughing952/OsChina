package com.laughing.android.tablayoutlaughing.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.laughing.android.tablayoutlaughing.LitePalBean.Student;
import com.laughing.android.tablayoutlaughing.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class SearchViewStudentActivity extends AppCompatActivity {

    private Context mContext = this;
    private SearchView mSearchView;
    private ListView mListView;
    List<String> strList = new ArrayList<>();
    private ArrayList<Student> mStudents = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("大乐教育    搜索学生");
        setContentView(R.layout.activity_search_view_student);
        initListData();//先把所有同学信息查询出来放入集合中转换成数组，最后放入适配器中
        initView();
        initListener();
    }

    private void initListData() {
        mStudents = (ArrayList<Student>) DataSupport.where("name like ?", "%" + "" + "%").find(Student.class);
//        if (mStudents != null) {
//            for (int i = 0; i < mStudents.size(); i++) {
//                Student student = mStudents.get(i);
//                strList.add(student.getName());
//
//            }
//        }

    }

    private void initListener() {
        // 设置搜索文本监听
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {

                    mListView.setFilterText(newText);
                } else {
                    mListView.clearTextFilter();
                }
                return false;
            }
        });

    }

    private void initView() {
        mSearchView = (SearchView) findViewById(R.id.searchView);
        mSearchView .setQueryHint("请输入学生姓名");
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new MyListAdapter());
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

        /**
         * 获取item类型
         *
         * @param position
         * @return
         */
        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        /**
         * 获取类型种类
         *
         * @return
         */
        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_student_list, null);
            }
            ViewHolder holder = ViewHolder.getHolder(convertView);
            Student student = mStudents.get(position);
            holder.name.setText(student.getName());
//            Product product = products.get(position);//获取集合数据
//            //设置数据
//            holder.p_minzouzi.setText(product.minTouMoney);
//            holder.p_money.setText(product.money);
//            holder.p_name.setText(product.name);
//            holder.p_suodingdays.setText(product.suodingDays);
//            holder.p_yearlv.setText(product.yearLv);
//            holder.p_progresss.setProgress(Integer.parseInt(product.progress));
            return convertView;
        }
    }

    public static class ViewHolder {
        TextView name;

        public ViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.tv_student_name);
//            p_money = (TextView) convertView.findViewById(R.id.p_money);
//            p_yearlv = (TextView) convertView.findViewById(R.id.p_yearlv);
//            p_suodingdays = (TextView) convertView.findViewById(R.id.p_suodingdays);
//            p_minzouzi = (TextView) convertView.findViewById(R.id.p_minzouzi);
//            p_progresss = (RoundProgress) convertView.findViewById(R.id.p_progresss);
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
