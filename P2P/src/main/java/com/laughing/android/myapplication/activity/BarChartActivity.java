package com.laughing.android.myapplication.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.laughing.android.myapplication.R;

import java.util.ArrayList;
/**
 * 柱状图
 */
public class BarChartActivity extends BaseActivity {


    private BarChart chart;
    private ImageView mTitle_left;
    private Typeface mTf;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bar_chart;
    }

    @Override
    protected void initView(View mContextView) {
        TextView title_tv = (TextView) findViewById(R.id.title_tv);
        mTitle_left = (ImageView) findViewById(R.id.title_left);
        ImageView title_right = (ImageView) findViewById(R.id.title_right);
        title_tv.setText("柱状图示例");
        mTitle_left.setVisibility(View.VISIBLE);
        title_right.setVisibility(View.INVISIBLE);
        chart = (BarChart) findViewById(R.id.chart);

    }

    @Override
    protected void initListener() {
        mTitle_left.setOnClickListener(new View.OnClickListener() {//回退按钮
            @Override
            public void onClick(View v) {
                closeCurrentActivity();
            }
        });
    }

    @Override
    protected void initData() {
        drawBarChart();
    }

    /// //////////////////////////////////////////////////////////////////////


    private void drawBarChart() {

        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        // apply styling
        chart.setDescription("柱状图示例");
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);//设置字体
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);//画x轴

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        leftAxis.setLabelCount(5, false);
        //绘制Y轴方向上最高的顶部的值距离整个图表的top距离
        leftAxis.setSpaceTop(20f);

//        chart.getAxisRight().setEnabled(false);//设置右边Y轴不显示
        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setTypeface(mTf);
        rightAxis.setLabelCount(5, false);
        rightAxis.setSpaceTop(20f);

        //添加数据
        BarData mChartData = generateDataBar();
        mChartData.setValueTypeface(mTf);
        // set data
        chart.setData(mChartData);
        // do not forget to refresh the chart
//        holder.chart.invalidate();
        chart.animateY(700);
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private BarData generateDataBar() {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry((int) (Math.random() * 70) + 30, i));
        }
        BarDataSet d = new BarDataSet(entries, "New DataSet 1");
        //绘制数据集合时指定属性
        d.setBarSpacePercent(50f); //柱状块之间的相隔距离
        d.setValueTextSize(10);//设置文字大小
        d.setValueTextColor(Color.BLUE);//文字颜色
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);
        BarData cd = new BarData(getMonths(), d);
        return cd;
    }

    private ArrayList<String> getMonths() {
        ArrayList<String> m = new ArrayList<String>();
        m.add("Jan");
        m.add("Feb");
        m.add("Mar");
        m.add("Apr");
        m.add("May");
        m.add("Jun");
        m.add("Jul");
        m.add("Aug");
        m.add("Sep");
        m.add("Okt");
        m.add("Nov");
        m.add("Dec");
        return m;
    }

}
