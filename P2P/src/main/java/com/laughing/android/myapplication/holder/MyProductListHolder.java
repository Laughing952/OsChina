package com.laughing.android.myapplication.holder;

import android.view.View;
import android.widget.TextView;

import com.laughing.android.myapplication.R;
import com.laughing.android.myapplication.bean.Product;
import com.laughing.android.myapplication.utils.UIUtils;
import com.laughing.android.myapplication.view.RoundProgress;

/**
 * 作者：Laughing on 2016/8/12 09:49
 * 邮箱：719240226@qq.com
 */

public class MyProductListHolder extends BaseHolder<Product> {
    private Product product;
    private TextView p_name, p_money, p_yearlv, p_suodingdays, p_minzouzi;
    private RoundProgress p_progresss;
    private View mView;

    @Override
    public View getRootView() {
        mView = UIUtils.getXmlView(R.layout.item_product_list);
        return mView;
    }

    @Override
    public <Product> void setData(Product product) {
        this.product = (com.laughing.android.myapplication.bean.Product) product;
        p_name = (TextView) mView.findViewById(R.id.p_name);
        p_money = (TextView) mView.findViewById(R.id.p_money);
        p_yearlv = (TextView) mView.findViewById(R.id.p_yearlv);
        p_suodingdays = (TextView) mView.findViewById(R.id.p_suodingdays);
        p_minzouzi = (TextView) mView.findViewById(R.id.p_minzouzi);
        p_progresss = (RoundProgress) mView.findViewById(R.id.p_progresss);
    }


    @Override
    public void refreshData() {
        //设置数据
        p_minzouzi.setText(product.minTouMoney);
        p_money.setText(product.money);
        p_name.setText(product.name);
        p_suodingdays.setText(product.suodingDays);
        p_yearlv.setText(product.yearLv);
        p_progresss.setProgress(Integer.parseInt(product.progress));
    }

}
