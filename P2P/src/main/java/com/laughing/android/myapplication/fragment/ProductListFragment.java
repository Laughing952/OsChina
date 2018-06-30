package com.laughing.android.myapplication.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laughing.android.myapplication.R;
import com.laughing.android.myapplication.adapter.MyBaseAdapter;
import com.laughing.android.myapplication.adapter.MySimpleBaseAdapter;
import com.laughing.android.myapplication.bean.Product;
import com.laughing.android.myapplication.common.AppNetConfig;
import com.laughing.android.myapplication.holder.BaseHolder;
import com.laughing.android.myapplication.holder.MyProductListHolder;
import com.laughing.android.myapplication.utils.UIUtils;
import com.laughing.android.myapplication.view.RoundProgress;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

/**
 * 作者：Laughing on 2017/8/11 15:13
 * 邮箱：719240226@qq.com
 */

public class ProductListFragment extends Fragment {

    private ListView mLv;
    private AsyncHttpClient client = new AsyncHttpClient();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        // 测试用的布局，用代码实现
//        TextView view = new TextView(getActivity());
//                view.setText(getClass().getSimpleName());
//                view.setTextSize(16);
//                view.setTextColor(Colo.RED);
//                view.setGravity(Gravity.CENTER);
        View view = UIUtils.getXmlView(R.layout.fragment_product_list);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        client.post(AppNetConfig.PRODUCT, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);
                JSONObject jsonObject = JSON.parseObject(content);

                Boolean success = jsonObject.getBoolean("success");
                if (success) {
                    String data = jsonObject.getString("data");
                    List<Product> products = JSON.parseArray(data, Product.class);
//                    mLv.setAdapter(new MyListAdapter(products));
//                    mLv.setAdapter(new MyListAdapter2(products));
                    mLv.setAdapter(new MyAdapter(products));

                }
            }

            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
            }
        });

    }

    private void initView(View view) {
        mLv = (ListView) view.findViewById(R.id.lv);
        mLv.setOverScrollMode(ListView.OVER_SCROLL_NEVER);//永远不显示listView滑动蓝色阴影
    }

    /**
     * 面向holder编程的Adapter
     */
    private class MyAdapter extends MyBaseAdapter<Product> {

        public MyAdapter(List<Product> list) {
            super(list);
        }

        @Override
        protected BaseHolder getHolder() {
            return new MyProductListHolder();
        }
    }

    private class MyListAdapter2 extends MySimpleBaseAdapter<Product> {

        public MyListAdapter2(List<Product> list) {
            super(list);
        }

        @Override
        protected View getYourView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = UIUtils.getXmlView(R.layout.item_product_list);
            }
            ViewHolder holder = ViewHolder.getHolder(convertView);
            Product product = list.get(position);//获取集合数据
            //设置数据
            holder.p_minzouzi.setText(product.minTouMoney);
            holder.p_money.setText(product.money);
            holder.p_name.setText(product.name);
            holder.p_suodingdays.setText(product.suodingDays);
            holder.p_yearlv.setText(product.yearLv);
            holder.p_progresss.setProgress(Integer.parseInt(product.progress));
            return convertView;
        }
    }

    private class MyListAdapter extends BaseAdapter {
        private List<Product> products;

        public MyListAdapter(List<Product> products) {
            this.products = products;
        }

        @Override
        public int getCount() {
            return products == null ? 0 : products.size();
        }

        @Override
        public Object getItem(int position) {
            return products.get(position);
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
                convertView = UIUtils.getXmlView(R.layout.item_product_list);
            }
            ViewHolder holder = ViewHolder.getHolder(convertView);
            Product product = products.get(position);//获取集合数据
            //设置数据
            holder.p_minzouzi.setText(product.minTouMoney);
            holder.p_money.setText(product.money);
            holder.p_name.setText(product.name);
            holder.p_suodingdays.setText(product.suodingDays);
            holder.p_yearlv.setText(product.yearLv);
            holder.p_progresss.setProgress(Integer.parseInt(product.progress));
            return convertView;
        }
    }

    public static class ViewHolder {
        TextView p_name, p_money, p_yearlv, p_suodingdays, p_minzouzi;
        RoundProgress p_progresss;

        public ViewHolder(View convertView) {
            p_name = (TextView) convertView.findViewById(R.id.p_name);
            p_money = (TextView) convertView.findViewById(R.id.p_money);
            p_yearlv = (TextView) convertView.findViewById(R.id.p_yearlv);
            p_suodingdays = (TextView) convertView.findViewById(R.id.p_suodingdays);
            p_minzouzi = (TextView) convertView.findViewById(R.id.p_minzouzi);
            p_progresss = (RoundProgress) convertView.findViewById(R.id.p_progresss);
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
